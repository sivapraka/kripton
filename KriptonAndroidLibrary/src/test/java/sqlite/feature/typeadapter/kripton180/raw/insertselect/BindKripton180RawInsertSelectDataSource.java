package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.typeadapter.kripton180.EmployeeTable;

/**
 * <p>
 * Represents implementation of datasource Kripton180RawInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180RawInsertSelectDataSource
 * @see BindKripton180RawInsertSelectDaoFactory
 * @see EmployeeRawInsertSelectDao
 * @see EmployeeRawInsertSelectDaoImpl
 * @see Employee
 */
public class BindKripton180RawInsertSelectDataSource extends AbstractDataSource implements BindKripton180RawInsertSelectDaoFactory, Kripton180RawInsertSelectDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindKripton180RawInsertSelectDataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected EmployeeRawInsertSelectDaoImpl employeeRawInsertSelectDao = new EmployeeRawInsertSelectDaoImpl(this);

  protected BindKripton180RawInsertSelectDataSource(DataSourceOptions options) {
    super("kripton180.db", 1, options);
  }

  @Override
  public EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao() {
    return employeeRawInsertSelectDao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase connection=openWritableDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (transaction!=null) transaction.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindKripton180RawInsertSelectDataSource instance() {
    if (instance==null) {
      instance=new BindKripton180RawInsertSelectDataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindKripton180RawInsertSelectDataSource open() {
    BindKripton180RawInsertSelectDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindKripton180RawInsertSelectDataSource openReadOnly() {
    BindKripton180RawInsertSelectDataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
    database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      Logger.info("DDL: %s",EmployeeTable.CREATE_TABLE_SQL);
      database.execSQL(EmployeeTable.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindKripton180RawInsertSelectDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindKripton180RawInsertSelectDataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindKripton180RawInsertSelectDaoFactory> {
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}
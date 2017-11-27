package sqlite.quickstart.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.quickstart.model.User;
import sqlite.quickstart.model.UserTable;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>
 *
 *  @see User
 *  @see UserDao
 *  @see UserTable
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_ALL_SQL1 = "SELECT id, name, username, email, address, phone, website, company FROM user ORDER BY username asc";

  private static final String SELECT_BY_ID_SQL2 = "SELECT id, name, username, email, address, phone, website, company FROM user WHERE id = ?";

  public UserDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO user (id, name, username, email, address, phone, website, company) VALUES (${bean.id}, ${bean.name}, ${bean.username}, ${bean.email}, ${bean.address}, ${bean.phone}, ${bean.website}, ${bean.company})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>${bean.username}</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * 	<dt>website</dt><dd>is mapped to <strong>${bean.website}</strong></dd>
   * 	<dt>company</dt><dd>is mapped to <strong>${bean.company}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(User bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO user (id, name, username, email, address, phone, website, company) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("id", bean.id);
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.username!=null) {
      _contentValues.put("username", bean.username);
    } else {
      _contentValues.putNull("username");
    }
    if (bean.email!=null) {
      _contentValues.put("email", bean.email);
    } else {
      _contentValues.putNull("email");
    }
    if (bean.address!=null) {
      _contentValues.put("address", UserTable.serializeAddress(bean.address));
    } else {
      _contentValues.putNull("address");
    }
    if (bean.phone!=null) {
      _contentValues.put("phone", bean.phone);
    } else {
      _contentValues.putNull("phone");
    }
    if (bean.website!=null) {
      _contentValues.put("website", bean.website);
    } else {
      _contentValues.putNull("website");
    }
    if (bean.company!=null) {
      _contentValues.put("company", UserTable.serializeCompany(bean.company));
    } else {
      _contentValues.putNull("company");
    }

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO user (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, username, email, address, phone, website, company FROM user ORDER BY username asc</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<User> resultList=new ArrayList<User>(cursor.getCount());
      User resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("username");
        int index3=cursor.getColumnIndex("email");
        int index4=cursor.getColumnIndex("address");
        int index5=cursor.getColumnIndex("phone");
        int index6=cursor.getColumnIndex("website");
        int index7=cursor.getColumnIndex("company");

        do
         {
          resultBean=new User();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.username=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5); }
          if (!cursor.isNull(index6)) { resultBean.website=cursor.getString(index6); }
          if (!cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, username, email, address, phone, website, company FROM user WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${value}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public User selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      User resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("username");
        int index3=cursor.getColumnIndex("email");
        int index4=cursor.getColumnIndex("address");
        int index5=cursor.getColumnIndex("phone");
        int index6=cursor.getColumnIndex("website");
        int index7=cursor.getColumnIndex("company");

        resultBean=new User();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.username=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.website=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7)); }

      }
      return resultBean;
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}

package sqlite.test05firt_aid;

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

/**
 * <p>
 * DAO implementation for entity <code>FirstAid</code>, based on interface <code>FirstAidDao</code>
 * </p>
 *
 *  @see FirstAid
 *  @see FirstAidDao
 *  @see FirstAidTable
 */
public class FirstAidDaoImpl extends AbstractDao implements FirstAidDao {
  private static final String SELECT_ALL_SQL1 = "SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid ORDER BY description";

  private static SQLiteStatement deleteAllPreparedStatement0;

  private static SQLiteStatement insertPreparedStatement1;

  public FirstAidDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid ORDER BY description</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>info</dt><dd>is associated to bean's property <strong>info</strong></dd>
   * 	<dt>longitude</dt><dd>is associated to bean's property <strong>longitude</strong></dd>
   * 	<dt>latitude</dt><dd>is associated to bean's property <strong>latitude</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>address2</dt><dd>is associated to bean's property <strong>address2</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>total_patient_count</dt><dd>is associated to bean's property <strong>totalPatientCount</strong></dd>
   * 	<dt>white_waiting_patients</dt><dd>is associated to bean's property <strong>whiteWaitingPatients</strong></dd>
   * 	<dt>white_visiting_patients</dt><dd>is associated to bean's property <strong>whiteVisitingPatients</strong></dd>
   * 	<dt>white_average_waiting_time</dt><dd>is associated to bean's property <strong>whiteAverageWaitingTime</strong></dd>
   * 	<dt>green_waiting_patients</dt><dd>is associated to bean's property <strong>greenWaitingPatients</strong></dd>
   * 	<dt>green_visiting_patients</dt><dd>is associated to bean's property <strong>greenVisitingPatients</strong></dd>
   * 	<dt>green_average_waiting_time</dt><dd>is associated to bean's property <strong>greenAverageWaitingTime</strong></dd>
   * 	<dt>yellow_waiting_patients</dt><dd>is associated to bean's property <strong>yellowWaitingPatients</strong></dd>
   * 	<dt>yellow_visiting_patients</dt><dd>is associated to bean's property <strong>yellowVisitingPatients</strong></dd>
   * 	<dt>yellow_average_waiting_time</dt><dd>is associated to bean's property <strong>yellowAverageWaitingTime</strong></dd>
   * 	<dt>red_waiting_patients</dt><dd>is associated to bean's property <strong>redWaitingPatients</strong></dd>
   * 	<dt>red_average_waiting_time</dt><dd>is associated to bean's property <strong>redAverageWaitingTime</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<FirstAid> selectAll() {
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

      ArrayList<FirstAid> resultList=new ArrayList<FirstAid>(cursor.getCount());
      FirstAid resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("uid");
        int index2=cursor.getColumnIndex("description");
        int index3=cursor.getColumnIndex("info");
        int index4=cursor.getColumnIndex("longitude");
        int index5=cursor.getColumnIndex("latitude");
        int index6=cursor.getColumnIndex("address");
        int index7=cursor.getColumnIndex("address2");
        int index8=cursor.getColumnIndex("city");
        int index9=cursor.getColumnIndex("phone");
        int index10=cursor.getColumnIndex("total_patient_count");
        int index11=cursor.getColumnIndex("white_waiting_patients");
        int index12=cursor.getColumnIndex("white_visiting_patients");
        int index13=cursor.getColumnIndex("white_average_waiting_time");
        int index14=cursor.getColumnIndex("green_waiting_patients");
        int index15=cursor.getColumnIndex("green_visiting_patients");
        int index16=cursor.getColumnIndex("green_average_waiting_time");
        int index17=cursor.getColumnIndex("yellow_waiting_patients");
        int index18=cursor.getColumnIndex("yellow_visiting_patients");
        int index19=cursor.getColumnIndex("yellow_average_waiting_time");
        int index20=cursor.getColumnIndex("red_waiting_patients");
        int index21=cursor.getColumnIndex("red_average_waiting_time");

        do
         {
          resultBean=new FirstAid();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.description=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.info=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.longitude=cursor.getFloat(index4); }
          if (!cursor.isNull(index5)) { resultBean.latitude=cursor.getFloat(index5); }
          if (!cursor.isNull(index6)) { resultBean.address=cursor.getString(index6); }
          if (!cursor.isNull(index7)) { resultBean.address2=cursor.getString(index7); }
          if (!cursor.isNull(index8)) { resultBean.city=cursor.getString(index8); }
          if (!cursor.isNull(index9)) { resultBean.phone=cursor.getString(index9); }
          if (!cursor.isNull(index10)) { resultBean.totalPatientCount=cursor.getInt(index10); }
          if (!cursor.isNull(index11)) { resultBean.whiteWaitingPatients=cursor.getInt(index11); }
          if (!cursor.isNull(index12)) { resultBean.whiteVisitingPatients=cursor.getInt(index12); }
          if (!cursor.isNull(index13)) { resultBean.whiteAverageWaitingTime=cursor.getString(index13); }
          if (!cursor.isNull(index14)) { resultBean.greenWaitingPatients=cursor.getInt(index14); }
          if (!cursor.isNull(index15)) { resultBean.greenVisitingPatients=cursor.getInt(index15); }
          if (!cursor.isNull(index16)) { resultBean.greenAverageWaitingTime=cursor.getString(index16); }
          if (!cursor.isNull(index17)) { resultBean.yellowWaitingPatients=cursor.getInt(index17); }
          if (!cursor.isNull(index18)) { resultBean.yellowVisitingPatients=cursor.getInt(index18); }
          if (!cursor.isNull(index19)) { resultBean.yellowAverageWaitingTime=cursor.getString(index19); }
          if (!cursor.isNull(index20)) { resultBean.redWaitingPatients=cursor.getInt(index20); }
          if (!cursor.isNull(index21)) { resultBean.redAverageWaitingTime=cursor.getString(index21); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM first_aid WHERE 1=1</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   *
   * @return number of deleted records
   */
  @Override
  public int deleteAll() {
    if (deleteAllPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM first_aid WHERE 1=1";
      deleteAllPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement0);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM first_aid WHERE 1=1");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES (${bean.uid}, ${bean.description}, ${bean.info}, ${bean.longitude}, ${bean.latitude}, ${bean.address}, ${bean.address2}, ${bean.city}, ${bean.phone}, ${bean.totalPatientCount}, ${bean.whiteWaitingPatients}, ${bean.whiteVisitingPatients}, ${bean.whiteAverageWaitingTime}, ${bean.greenWaitingPatients}, ${bean.greenVisitingPatients}, ${bean.greenAverageWaitingTime}, ${bean.yellowWaitingPatients}, ${bean.yellowVisitingPatients}, ${bean.yellowAverageWaitingTime}, ${bean.redWaitingPatients}, ${bean.redAverageWaitingTime})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>description</dt><dd>is mapped to <strong>${bean.description}</strong></dd>
   * 	<dt>info</dt><dd>is mapped to <strong>${bean.info}</strong></dd>
   * 	<dt>longitude</dt><dd>is mapped to <strong>${bean.longitude}</strong></dd>
   * 	<dt>latitude</dt><dd>is mapped to <strong>${bean.latitude}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>address2</dt><dd>is mapped to <strong>${bean.address2}</strong></dd>
   * 	<dt>city</dt><dd>is mapped to <strong>${bean.city}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * 	<dt>total_patient_count</dt><dd>is mapped to <strong>${bean.totalPatientCount}</strong></dd>
   * 	<dt>white_waiting_patients</dt><dd>is mapped to <strong>${bean.whiteWaitingPatients}</strong></dd>
   * 	<dt>white_visiting_patients</dt><dd>is mapped to <strong>${bean.whiteVisitingPatients}</strong></dd>
   * 	<dt>white_average_waiting_time</dt><dd>is mapped to <strong>${bean.whiteAverageWaitingTime}</strong></dd>
   * 	<dt>green_waiting_patients</dt><dd>is mapped to <strong>${bean.greenWaitingPatients}</strong></dd>
   * 	<dt>green_visiting_patients</dt><dd>is mapped to <strong>${bean.greenVisitingPatients}</strong></dd>
   * 	<dt>green_average_waiting_time</dt><dd>is mapped to <strong>${bean.greenAverageWaitingTime}</strong></dd>
   * 	<dt>yellow_waiting_patients</dt><dd>is mapped to <strong>${bean.yellowWaitingPatients}</strong></dd>
   * 	<dt>yellow_visiting_patients</dt><dd>is mapped to <strong>${bean.yellowVisitingPatients}</strong></dd>
   * 	<dt>yellow_average_waiting_time</dt><dd>is mapped to <strong>${bean.yellowAverageWaitingTime}</strong></dd>
   * 	<dt>red_waiting_patients</dt><dd>is mapped to <strong>${bean.redWaitingPatients}</strong></dd>
   * 	<dt>red_average_waiting_time</dt><dd>is mapped to <strong>${bean.redAverageWaitingTime}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(FirstAid bean) {
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    if (bean.uid!=null) {
      _contentValues.put("uid", bean.uid);
    } else {
      _contentValues.putNull("uid");
    }
    if (bean.description!=null) {
      _contentValues.put("description", bean.description);
    } else {
      _contentValues.putNull("description");
    }
    if (bean.info!=null) {
      _contentValues.put("info", bean.info);
    } else {
      _contentValues.putNull("info");
    }
    if (bean.longitude!=null) {
      _contentValues.put("longitude", bean.longitude);
    } else {
      _contentValues.putNull("longitude");
    }
    if (bean.latitude!=null) {
      _contentValues.put("latitude", bean.latitude);
    } else {
      _contentValues.putNull("latitude");
    }
    if (bean.address!=null) {
      _contentValues.put("address", bean.address);
    } else {
      _contentValues.putNull("address");
    }
    if (bean.address2!=null) {
      _contentValues.put("address2", bean.address2);
    } else {
      _contentValues.putNull("address2");
    }
    if (bean.city!=null) {
      _contentValues.put("city", bean.city);
    } else {
      _contentValues.putNull("city");
    }
    if (bean.phone!=null) {
      _contentValues.put("phone", bean.phone);
    } else {
      _contentValues.putNull("phone");
    }
    _contentValues.put("total_patient_count", bean.totalPatientCount);
    _contentValues.put("white_waiting_patients", bean.whiteWaitingPatients);
    _contentValues.put("white_visiting_patients", bean.whiteVisitingPatients);
    if (bean.whiteAverageWaitingTime!=null) {
      _contentValues.put("white_average_waiting_time", bean.whiteAverageWaitingTime);
    } else {
      _contentValues.putNull("white_average_waiting_time");
    }
    _contentValues.put("green_waiting_patients", bean.greenWaitingPatients);
    _contentValues.put("green_visiting_patients", bean.greenVisitingPatients);
    if (bean.greenAverageWaitingTime!=null) {
      _contentValues.put("green_average_waiting_time", bean.greenAverageWaitingTime);
    } else {
      _contentValues.putNull("green_average_waiting_time");
    }
    _contentValues.put("yellow_waiting_patients", bean.yellowWaitingPatients);
    _contentValues.put("yellow_visiting_patients", bean.yellowVisitingPatients);
    if (bean.yellowAverageWaitingTime!=null) {
      _contentValues.put("yellow_average_waiting_time", bean.yellowAverageWaitingTime);
    } else {
      _contentValues.putNull("yellow_average_waiting_time");
    }
    _contentValues.put("red_waiting_patients", bean.redWaitingPatients);
    if (bean.redAverageWaitingTime!=null) {
      _contentValues.put("red_average_waiting_time", bean.redAverageWaitingTime);
    } else {
      _contentValues.putNull("red_average_waiting_time");
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
      Logger.info("INSERT INTO first_aid (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
    bean.id=result;

    return (int)result;
  }

  public static void clearCompiledStatements() {
    if (deleteAllPreparedStatement0!=null) {
      deleteAllPreparedStatement0.close();
      deleteAllPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
  }
}

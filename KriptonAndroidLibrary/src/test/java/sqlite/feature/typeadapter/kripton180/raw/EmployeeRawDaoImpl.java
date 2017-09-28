package sqlite.feature.typeadapter.kripton180.raw;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterAddress;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;

/**
 * <p>
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeRawDao</code>
 * </p>
 *
 *  @see Employee
 *  @see EmployeeRawDao
 *  @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeRawDaoImpl extends AbstractDao implements EmployeeRawDao {
  public EmployeeRawDaoImpl(BindKripton180RawDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectById(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByIdJQL(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is binded to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is binded to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is binded to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is binded to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is binded to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is binded to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is binded to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is binded to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param fieldByte
   * 	is binded to <code>${fieldByte}</code>
   * @param fieldCharacter
   * 	is binded to <code>${fieldCharacter}</code>
   * @param fieldShort
   * 	is binded to <code>${fieldShort}</code>
   * @param fieldInteger
   * 	is binded to <code>${fieldInteger}</code>
   * @param fieldLong
   * 	is binded to <code>${fieldLong}</code>
   * @param fieldFloat
   * 	is binded to <code>${fieldFloat}</code>
   * @param fieldDouble
   * 	is binded to <code>${fieldDouble}</code>
   * @param fieldString
   * 	is binded to <code>${fieldString}</code>
   * @param fieldByteArray
   * 	is binded to <code>${fieldByteArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAll(long id, String fieldByte, String fieldCharacter, String fieldShort,
      String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble,
      String fieldString, String fieldByteArray) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    _sqlWhereParams.add((fieldByte==null?"":fieldByte));
    _sqlWhereParams.add((fieldCharacter==null?"":fieldCharacter));
    _sqlWhereParams.add((fieldShort==null?"":fieldShort));
    _sqlWhereParams.add((fieldInteger==null?"":fieldInteger));
    _sqlWhereParams.add((fieldLong==null?"":fieldLong));
    _sqlWhereParams.add((fieldFloat==null?"":fieldFloat));
    _sqlWhereParams.add((fieldDouble==null?"":fieldDouble));
    _sqlWhereParams.add((fieldString==null?"":fieldString));
    _sqlWhereParams.add((fieldByteArray==null?"":fieldByteArray));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }
}

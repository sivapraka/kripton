package com.abubusoft.kripton.processor.kripton58.list;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>DoubleBean</code>, based on interface <code>DoubleDao</code>
 * </p>
 *
 *  @see DoubleBean
 *  @see DoubleDao
 *  @see DoubleBeanTable
 */
public class DoubleDaoImpl extends AbstractDao implements DoubleDao {
  public DoubleDaoImpl(BindDoubleDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM double_bean WHERE 1=1</pre>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public DoubleBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM double_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM double_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    DoubleBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new DoubleBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Double>(), Double.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Double>(), Double.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM double_bean WHERE value=${value}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public DoubleBean selectOne(List<Double> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM double_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM double_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    DoubleBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new DoubleBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Double>(), Double.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Double>(), Double.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM double_bean WHERE value=${value}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param listener
   */
  @Override
  public void selectOne(List<Double> value, OnReadBeanListener<DoubleBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM double_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM double_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    DoubleBean resultBean=new DoubleBean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Double>(), Double.class, cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Double>(), Double.class, cursor.getBlob(index2)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM double_bean WHERE value=${value}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong></li>
   * 	<li><strong>value</strong></li>
   * 	<li><strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param listener
   */
  @Override
  public void selectOne(List<Double> value, OnReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM double_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM double_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM double_bean WHERE value=${value}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<DoubleBean> selectList(List<Double> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM double_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM double_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<DoubleBean> resultList=new LinkedList<DoubleBean>();
    DoubleBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new DoubleBean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Double>(), Double.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Double>(), Double.class, cursor.getBlob(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL Update used:</p>
   * <pre>UPDATE double_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to parameter <strong>value</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * 	<dt>${paramValue}</dt><dd>is mapped to parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(List<Double> value, Double id, List<Double> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={(id==null?null:String.valueOf(id)), (paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE double_bean SET value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s"), (Object[])whereConditions);
    int result = database().update("double_bean", contentValues, "id=? and value=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO double_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is mapped to parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, List<Double> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO double_bean (id, value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("double_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO double_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>bean.value</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>bean.value2</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(DoubleBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(bean.value));
    } else {
      contentValues.putNull("value");
    }

    if (bean.value2!=null) {
      contentValues.put("value2", ProcessorHelper.asByteArray(bean.value2));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO double_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("double_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE double_bean WHERE value=${paramValue}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${paramValue}</dt><dd>is mapped to parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Double> paramValue) {
    String[] whereConditions={(paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE double_bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("double_bean", "value=?", whereConditions);
    return result;
  }
}

package com.abubusoft.kripton.processor.test04primary_key;

import android.database.Cursor;
import com.abubusoft.kripton.android.sqlite.AbstractBindDao;
import com.abubusoft.kripton.common.Logger;
import com.abubusoft.kripton.common.StringUtil;

/**
 * Generated by Kripton Library.
 *
 * @since Wed Jun 08 00:13:40 CEST 2016
 *
 */
public class BindDaoBean01 extends AbstractBindDao implements DaoBean01 {
  /**
   * <p>Select query is:</p>
   * <pre>select id, text from bean01 where id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("select id, text from bean01 where id='%s'"),(Object[])args);
    Cursor cursor = database.rawQuery("select id, text from bean01 where id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
   *
   * @param text
   * 	used as updated field
   * @param id
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(String text, long id) {
    contentValues.clear();

    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET text='"+StringUtil.checkSize(contentValues.get("text"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database.update("bean01", contentValues, "id=?", whereConditions);
    return result;
  }
}

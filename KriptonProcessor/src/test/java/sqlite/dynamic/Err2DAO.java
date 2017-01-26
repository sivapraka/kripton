package sqlite.dynamic;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err2DAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity);
	
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlWhere String name, @BindSqlWhere String name2, @BindSqlParam("nameTemp") String nameValue);
	
}
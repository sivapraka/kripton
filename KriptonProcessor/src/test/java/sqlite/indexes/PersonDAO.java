package sqlite.indexes;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

@BindDao(Person.class)
public interface PersonDAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, Date birthDay);

	@BindSqlSelect(orderBy="name")
	List<Person> selectAll();
	
	@BindSqlSelect(where="name like ${nameTemp} || '%' and birthDay < ${date}")
	List<Person> selectOne(@BindSqlParam("nameTemp") String nameValue, @BindSqlWhere String where, @BindSqlOrderBy String orderBy, Date date );
	
	@BindSqlSelect(orderBy="name")
	void selectBeanListener(OnReadBeanListener<Person> beanListener, @BindSqlOrderBy String orderBy);
	
//	@BindSqlSelect(orderBy="name")
//	void selectCursorListener(OnReadCursorListener cursorListener);
}
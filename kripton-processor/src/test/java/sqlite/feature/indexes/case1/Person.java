/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.indexes.case1;

import java.util.Date;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

/**
 * The Class Person.
 */
@BindType
@BindTable(
		indexes= {
				@BindIndex({"birthCity", "birthDay desc"}),
				@BindIndex({"surname"}),
				@BindIndex(value={"name","surname", "date desc"}, unique=true )
		}
)
public class Person {
  
  /** The id. */
  public long id;
   
  
  @BindSqlColumn(columnType=ColumnType.UNIQUE)
  public String aliasName;
  
  
  /** The date. */
  public String date;
  
  
  /** The name. */
  @BindSqlColumn(columnType=ColumnType.INDEXED)
  public String name;
  
  /** The surname. */
  @BindSqlColumn(columnType=ColumnType.INDEXED)
  public String surname;
  
  /** The birth city. */
  public String birthCity;
  
  /** The birth day. */
  public Date birthDay;
}
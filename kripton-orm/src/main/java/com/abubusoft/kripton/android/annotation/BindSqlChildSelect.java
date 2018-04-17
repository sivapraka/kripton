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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p></p>

 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface BindSqlChildSelect {
	
	/**
	 * field's name to use as parameter of dao's query to fill field annnotated with <code>BindRelation</code>
	 * @return
	 */
	String foreignKey();

	/**
	 * method's name of child entity of relation to invoke to fill field. This method must have only one parameter: the foreign key value
	 * 
	 * @return
	 * 		method of child dao to use 
	 */
	String method() default "";

}

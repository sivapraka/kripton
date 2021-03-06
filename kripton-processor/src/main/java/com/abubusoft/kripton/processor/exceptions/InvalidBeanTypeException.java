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
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

/**
 * The Class InvalidBeanTypeException.
 */
public class InvalidBeanTypeException extends KriptonProcessorException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8462705406839489618L;

	/**
	 * Instantiates a new invalid bean type exception.
	 *
	 * @param daoDefinition the dao definition
	 */
	public InvalidBeanTypeException(SQLiteDaoDefinition daoDefinition)
	{
		super(String.format("In class '%s' is used @%s annotation for unmanaged bean type '%s'. Please check if it has @%s annotation.", daoDefinition.getName(), BindDao.class.getSimpleName(), daoDefinition.getEntityClassName(), BindType.class.getSimpleName()));
	}
	
	/**
	 * Instantiates a new invalid bean type exception.
	 *
	 * @param caller the caller
	 * @param referred the referred
	 */
	public InvalidBeanTypeException(SQLProperty caller, String referred)
	{
		super(String.format("In class '%s', field '%s' uses unmanaged bean type '%s'. Please check if it has @%s annotation.", caller.getParent().getName(), caller.getName(), referred, BindType.class.getSimpleName()));
	}
}

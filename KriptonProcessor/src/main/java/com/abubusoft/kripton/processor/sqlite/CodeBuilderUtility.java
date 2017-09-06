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
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.PropertyInAnnotationNotFoundException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

import android.content.ContentValues;

public abstract class CodeBuilderUtility {

	/**
	 * Generate code necessary to put bean properties in content values map.
	 * Return primary key
	 * 
	 * @param elementUtils
	 * @param daoDefinition
	 * @param method
	 * @param methodBuilder
	 *            used to code generation
	 * @param alreadyUsedBeanPropertiesNames
	 *            optional
	 * @return primary key.
	 */
	public static List<SQLProperty> extractUsedProperties(SQLiteModelMethod method, Class<? extends Annotation> annotationClazz, Builder methodBuilder,
			List<String> alreadyUsedBeanPropertiesNames) {
		SQLDaoDefinition daoDefinition = method.getParent();
		SQLEntity entity = daoDefinition.getEntity();
		List<SQLProperty> listPropertyInContentValue = new ArrayList<SQLProperty>();
		
		Set<String> foundColumns = JQLChecker.getInstance().extractColumnsToInsertOrUpdate(method, method.jql.value, entity);		
		
		// for each property in entity except primaryKey and excluded properties
		for (SQLProperty item : entity.getCollection()) {
			if (foundColumns.size()>0 && !foundColumns.contains(item.getName())) {
				continue;
			}

			// add property to list of used properties
			listPropertyInContentValue.add(item);
		}

		return listPropertyInContentValue;

	}

	public static void generateContentValuesFromEntity(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLiteModelMethod method, Class<? extends Annotation> annotationClazz,
			Builder methodBuilder, List<String> alreadyUsedBeanPropertiesNames) {
		// all check is already done

		SQLEntity entity = daoDefinition.getEntity();

		String entityName = method.getParameters().get(0).value0;
		TypeName entityClassName = typeName(entity.getElement());

		methodBuilder.addCode("$T contentValues=contentValues();\n", ContentValues.class);
		methodBuilder.addCode("contentValues.clear();\n\n");

		Set<String> updateColumns = JQLChecker.getInstance().extractColumnsToInsertOrUpdate(method, method.jql.value, entity);
		SQLProperty item;
		for (String columnName: updateColumns) {
			item=entity.get(columnName);
			AssertKripton.assertTrueOrUnknownPropertyInJQLException(item!=null, method, columnName);
			
			if (TypeUtility.isNullable(item)) {
				methodBuilder.beginControlFlow("if ($L!=null)", getter(entityName, entityClassName, item));
			}

			// add property to list of used properties
			methodBuilder.addCode("contentValues.put($S, ", item.columnName);
			SQLTransformer.java2ContentValues(methodBuilder, entityClassName, entityName, item);
			methodBuilder.addCode(");\n");

			if (TypeUtility.isNullable(item)) {
				methodBuilder.nextControlFlow("else");
				methodBuilder.addCode("contentValues.putNull($S);\n", item.columnName);
				methodBuilder.endControlFlow();
			}
		}
		
		methodBuilder.addCode("\n");

	}

}

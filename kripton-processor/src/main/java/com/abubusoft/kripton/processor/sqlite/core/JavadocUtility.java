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
package com.abubusoft.kripton.processor.sqlite.core;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.Version;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator.JavadocPart;
import com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator.JavadocPartType;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility.SelectType;
import com.abubusoft.kripton.processor.sqlite.SqlAnalyzer;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

// TODO: Auto-generated Javadoc
/**
 * The Class JavadocUtility.
 */
public abstract class JavadocUtility {
	
	/**
	 * Generate javadoc generated by.
	 *
	 * @param builder the builder
	 */
	public static void generateJavadocGeneratedBy(Builder builder) {
		if (!BindDataSourceSubProcessor.JUNIT_TEST_MODE) {
			builder.addJavadoc("<p><strong>This class is generated by Kripton Annotation Processor - v. $L</strong></p>\n", Version.getVersion());
			builder.addJavadoc("<p><strong>Generation-time: $L</strong></p>\n\n", (new Date()).toString());
		}
	}

	/**
	 * Generate java doc for select.
	 *
	 * @param methodBuilder the method builder
	 * @param sqlParams the sql params
	 * @param method the method
	 * @param annotation the annotation
	 * @param fieldList the field list
	 * @param selectResultType the select result type
	 * @param javadocParts the javadoc parts
	 */
	public static void generateJavaDocForSelect(MethodSpec.Builder methodBuilder, List<String> sqlParams, final SQLiteModelMethod method, ModelAnnotation annotation, Set<JQLProjection> fieldList,
			SelectType selectResultType, JavadocPart... javadocParts) {
		final SQLiteDaoDefinition daoDefinition = method.getParent();
		final SQLiteEntity entity = daoDefinition.getEntity();
		TypeName beanTypeName = TypeName.get(daoDefinition.getEntity().getElement().asType());
				
		String sql = JQLChecker.getInstance().replace(method, method.jql, new JQLReplacerListenerImpl(method) {
	
			@Override
			public String onColumnName(String columnName) {				
				SQLProperty tempProperty = daoDefinition.getEntity().get(columnName);
				AssertKripton.assertTrueOrUnknownPropertyInJQLException(tempProperty != null, method, columnName);

				return tempProperty.columnName;
			}

		});

		Set<JQLProjection> projectedColumns = JQLChecker.getInstance().extractProjections(method, method.jql.value, entity);

		methodBuilder.addJavadoc("<h2>Select SQL:</h2>\n\n", annotation.getSimpleName());
		methodBuilder.addJavadoc("<pre>$L</pre>", sql);
		methodBuilder.addJavadoc("\n\n");

		// there will be alway some projected column
		{
			methodBuilder.addJavadoc("<h2>Projected columns:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (JQLProjection column : projectedColumns) {
				// KRIPTON_DEBUG field info only it exists
				if (column.column != null) {
					methodBuilder.addJavadoc("\t<dt>$L</dt>", column.property.columnName);
					// SQLProperty attribute = fieldList.value1.get(i);
					methodBuilder.addJavadoc("<dd>is associated to bean's property <strong>$L</strong></dd>", column.column);
				} else {
					methodBuilder.addJavadoc("\t<dt>$L</dt>", column.expression);
					methodBuilder.addJavadoc("<dd>no bean's property is associated</dd>");
				}
				methodBuilder.addJavadoc("\n");
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}
		
		// subqueries
		if (method.hasChildrenSelects()) {
			methodBuilder.addJavadoc("<h2>Fields filled by subqueries:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (Triple<String, String, SQLiteModelMethod> item: method.childrenSelects) {
				methodBuilder.addJavadoc("\t<dt>$L</dt>", item.value0);
				methodBuilder.addJavadoc("<dd>filled by query {@link $L#$L}</dd>\n", item.value2.getParent().getName(), item.value2.getName());
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// dynamic parts
		if (method.hasDynamicOrderByConditions() || method.hasDynamicWhereConditions() || method.hasDynamicPageSizeConditions()) {
			methodBuilder.addJavadoc("<h2>Method's parameters and associated dynamic parts:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			if (method.hasDynamicWhereConditions()) {
				methodBuilder.addJavadoc("<dt>$L</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{$L}</dd>\n", method.dynamicWhereParameterName,
						JQLDynamicStatementType.DYNAMIC_WHERE);
			}
			if (method.hasDynamicOrderByConditions()) {
				methodBuilder.addJavadoc("<dt>$L</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{$L}</dd>\n", method.dynamicOrderByParameterName,
						JQLDynamicStatementType.DYNAMIC_ORDER_BY);
			}
			if (method.hasDynamicPageSizeConditions()) {
				methodBuilder.addJavadoc("<dt>$L</dt>is part of limit statement resolved at runtime. In above SQL it is displayed as #{$L}</dd>\n", method.dynamicPageSizeName,
						JQLDynamicStatementType.DYNAMIC_PAGE_SIZE);
			}

			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// query parameters
		if (sqlParams.size() > 0) {
			methodBuilder.addJavadoc("<h2>Query's parameters:</h2>\n");
			methodBuilder.addJavadoc("<dl>\n");
			for (String param : sqlParams) {
				methodBuilder.addJavadoc("\t<dt>$L</dt><dd>is binded to method's parameter <strong>$L</strong></dd>\n", SqlAnalyzer.PARAM_PREFIX + param + SqlAnalyzer.PARAM_SUFFIX, method.findParameterNameByAlias(param));
			}
			methodBuilder.addJavadoc("</dl>");
			methodBuilder.addJavadoc("\n\n");
		}

		// method params
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();

			methodBuilder.addJavadoc("@param $L\n", parameterSpec.name);
			if (beanTypeName.equals(item.value1)) {
				methodBuilder.addJavadoc("\tis used as <code>$L</code>\n", SqlAnalyzer.PARAM_PREFIX + method.findParameterAliasByName(item.value0) + SqlAnalyzer.PARAM_SUFFIX);
			} else if (TypeUtility.isTypeEquals(item.value1, ParameterizedTypeName.get(TypeUtility.className(OnReadBeanListener.class), beanTypeName))) {
				methodBuilder.addJavadoc("\tis the $T listener\n", beanTypeName);
			} else if (TypeUtility.isTypeEquals(item.value1, TypeUtility.className(OnReadCursorListener.class))) {
				methodBuilder.addJavadoc("\tis the cursor listener\n", beanTypeName);
			} else if (item.value0.equals(method.dynamicWhereParameterName)) {
				methodBuilder.addJavadoc("\tis used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link $T#format})\n", StringUtils.class);
			} else if (item.value0.equals(method.dynamicOrderByParameterName)) {
				methodBuilder.addJavadoc("\tis used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link $T#format})\n", StringUtils.class);
			} else if (item.value0.equals(method.dynamicPageSizeName)) {
				methodBuilder.addJavadoc("\tis used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link $T#format})\n", StringUtils.class);
			} else {
				methodBuilder.addJavadoc("\tis binded to <code>$L</code>\n", SqlAnalyzer.PARAM_PREFIX + method.findParameterAliasByName(item.value0) + SqlAnalyzer.PARAM_SUFFIX);
			}
		}
		for (JavadocPart item : javadocParts) {
			if (item.javadocPartType != JavadocPartType.ADD_PARAMETER)
				continue;
			methodBuilder.addJavadoc("@param $L\n", item.name);
			methodBuilder.addJavadoc("\t$L\n", item.description);
		}

		for (JavadocPart item : javadocParts) {
			if (item.javadocPartType != JavadocPartType.RETURN)
				continue;
			methodBuilder.addJavadoc("@return $L\n", item.description);
			// override return
			return;
		}
		// return type
		switch (selectResultType) {
		case BEAN:
			methodBuilder.addJavadoc("@return selected bean or <code>null</code>.\n");
			break;
		case CURSOR:
			methodBuilder.addJavadoc("@return cursor. Closing the cursor is delegated to the calling code.\n");
			break;
		case LIST_BEAN:
			methodBuilder.addJavadoc("@return collection of bean or empty collection.\n");
			break;
		case LIST_SCALAR:
			methodBuilder.addJavadoc("@return collection of single value extracted by query.\n");
			break;
		case SCALAR:
			methodBuilder.addJavadoc("@return single value extracted by query.\n");
			break;
		case PAGED_RESULT:
			methodBuilder.addJavadoc("@return paginated result.\n");
			break;
		default:
			// case LISTENER_BEAN:
			// case LISTENER_CURSOR:
			break;
		}
	}
}

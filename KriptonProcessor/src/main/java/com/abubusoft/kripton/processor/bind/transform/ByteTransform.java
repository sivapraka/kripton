/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.bind.transform;


/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
class ByteTransform extends PrimitiveBindTransform {

	public ByteTransform(boolean nullable) {
		super(nullable);
		
		XML_TYPE = "Int";	
		XML_CAST_TYPE="(byte)";
		XML_UTILITY_TYPE="Byte";
		
		JSON_TYPE = "Number";
		JSON_PARSER_METHOD="getByteValue";
	}

}
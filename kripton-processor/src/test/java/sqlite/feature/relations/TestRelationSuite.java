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
package sqlite.feature.relations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

/**
 * The Class TestRXSuite.
 */
@RunWith(Suite.class)
// @formatter:off
@Suite.SuiteClasses({
	TestCompileRelation1.class,
	TestCompileRelation2.class,
	TestCompileRelation3.class,
	TestCompileRelation4.class,
	TestCompileRelationError1.class,
	TestCompileRelationError2.class,
	TestCompileRelationError3.class,
	TestCompileRelationError4.class,
	TestCompileRelationError5.class,
	TestCompileRelationError6.class,	})
// @formatter:on
public class TestRelationSuite extends BaseProcessorTest {

}

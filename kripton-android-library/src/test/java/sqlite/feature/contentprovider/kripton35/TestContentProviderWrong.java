/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.contentprovider.kripton35;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ContentProviderController;

import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import base.BaseAndroidTest;
import sqlite.feature.contentprovider.kripton35.entities.PersonTable;
import sqlite.feature.contentprovider.kripton35.persistence.BindPersonContentProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class TestContentProviderWrong.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestContentProviderWrong extends BaseAndroidTest {

	/**
	 * Setup content provider.
	 */
	@Before
	public void setupContentProvider() {
		ProviderInfo info = new ProviderInfo();
		info.authority = BindPersonContentProvider.AUTHORITY;

		ContentProviderController<BindPersonContentProvider> controller = Robolectric.buildContentProvider(BindPersonContentProvider.class);
		controller.create(info);
	}

	/**
	 * <p>
	 * This test fails because we try to insert ID column that is not specified
	 * between column that can be inserted with this operation.
	 */
	@Test(expected = KriptonRuntimeException.class)
	public void testFail1Insert() {
		ContentValues contentValues = new ContentValues();

		contentValues.put(PersonTable.COLUMN_ID, 25);
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		Uri uri = Uri.parse(BindPersonContentProvider.URI + "/persons");
		getApplicationContext().getContentResolver().insert(uri, contentValues);
	}

	/**
	 * <p>
	 * This test fails because we try to insert PARENT_ID column that is
	 * excluded in @BindSqlInsert.
	 */
	@Test(expected = KriptonRuntimeException.class)
	public void testFail2Insert() {
		ContentValues contentValues = new ContentValues();

		contentValues.put(PersonTable.COLUMN_PARENT_ID, 1);
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		Uri uri = Uri.parse(BindPersonContentProvider.URI + "/persons");
		getApplicationContext().getContentResolver().insert(uri, contentValues);
	}

}

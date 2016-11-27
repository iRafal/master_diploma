package com.medvid.andrii.diplomawork.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.BuildConfig;

public interface TableDefinitionContract<T extends ModelEntity> extends BaseColumns {

    String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS";

    String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";

    String PRIMARY_KEY = "PRIMARY KEY";

    String AUTOINCREMENT = "AUTOINCREMENT";

    String TEXT_TYPE = "TEXT";

    String INTEGER_TYPE = "INTEGER";

    String BOOLEAN_TYPE = "INTEGER";

    String REAL_TYPE = "REAL";

    String COMMA = ",";

    String SPACE = " ";

    String INTEGER_PRIMARY_KEY_ROW_DEFINITION = BaseColumns._ID + SPACE + INTEGER_TYPE + SPACE + PRIMARY_KEY;

    String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;

    String CONTENT_SCHEME = "content://";

    Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    String[] getColumns();

    ContentValues getContentValues(@NonNull T modelEntity);

    T getEntity(@NonNull Cursor cursor);
}

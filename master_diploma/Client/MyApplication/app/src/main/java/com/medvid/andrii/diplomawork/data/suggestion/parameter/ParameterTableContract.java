package com.medvid.andrii.diplomawork.data.suggestion.parameter;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class ParameterTableContract implements TableDefinitionContract<Parameter> {

    public static final String TABLE_NAME = "parameter";

    public static final String DESCRIPTION = "description";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ( "
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + ", "
                    + DESCRIPTION + " " + TEXT_TYPE
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_PARAMETER = 10;
    public static final int CODE_PARAMETER_ITEM = 11;
    public static final String CONTENT_PARAMETER_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_PARAMETER_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static ParameterTableContract getInstance()   {
        return new ParameterTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    private ParameterTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                DESCRIPTION
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Parameter parameter) {
        Preconditions.checkNotNull(parameter);

        ContentValues values = new ContentValues();
        values.put(_ID, parameter.getId());
        values.put(DESCRIPTION, parameter.getDescription());

        return values;
    }

    @Override
    public Parameter getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int descriptionIndex = cursor.getColumnIndexOrThrow(DESCRIPTION);

        int id = cursor.getInt(idIndex);
        String description = cursor.getString(descriptionIndex);

        return new Parameter(id, description);
    }
}

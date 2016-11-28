package com.medvid.andrii.diplomawork.data.suggestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;
import com.medvid.andrii.diplomawork.data.disease.Disease;
import com.medvid.andrii.diplomawork.data.suggestion.parameter.Parameter;

public class SuggestionTableContract implements TableDefinitionContract<Suggestion> {

    public static final String TABLE_NAME = "suggestion";

    public static final String DESCRIPTION = "description";
    public static final String DISEASE_ID = "disease_id";
    public static final String PARAMETER_ID = "parameter_id";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + SPACE + TABLE_NAME + SPACE + "("
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + COMMA
                    + DESCRIPTION + SPACE + TEXT_TYPE + COMMA
                    + DISEASE_ID + SPACE + INTEGER_TYPE + COMMA
                    + PARAMETER_ID + SPACE + INTEGER_TYPE
                    + SPACE + ")";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + SPACE + TABLE_NAME;

    public static final int CODE_SUGGESTION = 10;
    public static final int CODE_SUGGESTION_ITEM = 11;
    public static final String CONTENT_SUGGESTION_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_SUGGESTION_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static SuggestionTableContract getInstance()   {
        return new SuggestionTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    private SuggestionTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                DESCRIPTION,
                DISEASE_ID,
                PARAMETER_ID
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Suggestion suggestion) {
        Preconditions.checkNotNull(suggestion);

        ContentValues values = new ContentValues();
        values.put(_ID, suggestion.getId());
        values.put(DESCRIPTION, suggestion.getDescription());
        values.put(DISEASE_ID, suggestion.getDisease().getId());
        values.put(PARAMETER_ID, suggestion.getParameter().getId());

        return values;
    }

    @Override
    public Suggestion getEntity(@NonNull Cursor cursor) {

        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int descriptionIndex = cursor.getColumnIndexOrThrow(DESCRIPTION);
        int diseaseIdIndex = cursor.getColumnIndexOrThrow(DISEASE_ID);
        int parameterIdIndex = cursor.getColumnIndexOrThrow(PARAMETER_ID);

        int id = cursor.getInt(idIndex);
        String description = cursor.getString(descriptionIndex);
        int diseaseId = cursor.getInt(diseaseIdIndex);
        int parameterId = cursor.getInt(parameterIdIndex);

        return new Suggestion(
                id,
                description,
                new Disease(diseaseId, ""),
                new Parameter(parameterId, ""));
    }
}

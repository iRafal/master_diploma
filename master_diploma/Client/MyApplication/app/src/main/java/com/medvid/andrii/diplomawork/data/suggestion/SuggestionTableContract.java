package com.medvid.andrii.diplomawork.data.suggestion;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;
import com.medvid.andrii.diplomawork.data.forecast.ForecastTableContract;

public class SuggestionTableContract implements TableDefinitionContract<Suggestion> {

    public static final String TABLE_NAME = "suggestion";

    public static final String SUGGESTION_DESCRIPTION = "suggestion_description";
    public static final String PARAMETER_DESCRIPTION = "parameter_description";
    public static final String FORECAST_ID = "forecast_id";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ( "
                    + _ID + " " + INTEGER_TYPE + " " + NOT_NULL + " " + PRIMARY_KEY + " " + AUTOINCREMENT + ", "
                    + SUGGESTION_DESCRIPTION + " " + TEXT_TYPE + ", "
                    + PARAMETER_DESCRIPTION + " " + TEXT_TYPE + ", "
                    + FORECAST_ID + " " + INTEGER_TYPE + " " + NOT_NULL + ", "
                    + FOREIGN_KEY + " (" + FORECAST_ID + ") " + REFERENCES + " " + ForecastTableContract.TABLE_NAME + " (" + _ID + ")"
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_SUGGESTION = 4;
    public static final int CODE_SUGGESTION_ITEM = 5;
    public static final String CONTENT_SUGGESTION_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_SUGGESTION_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static SuggestionTableContract getInstance()   {
        return new SuggestionTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    public static Uri buildUriWith(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static Uri buildUriWith(String id) {
        return CONTENT_URI.buildUpon().appendPath(id).build();
    }

    private SuggestionTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                SUGGESTION_DESCRIPTION,
                PARAMETER_DESCRIPTION,
                FORECAST_ID
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Suggestion suggestion) {
        Preconditions.checkNotNull(suggestion);

        ContentValues values = new ContentValues();
        values.put(_ID, suggestion.getId());
        values.put(SUGGESTION_DESCRIPTION, suggestion.getSuggestionDescription());
        values.put(PARAMETER_DESCRIPTION, suggestion.getParameterDescription());
        values.put(FORECAST_ID, suggestion.getForecaseId());

        return values;
    }

    @Override
    public Suggestion getEntity(@NonNull Cursor cursor) {

        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int suggestionDescriptionIndex = cursor.getColumnIndexOrThrow(SUGGESTION_DESCRIPTION);
        int parameterDescriptionIdIndex = cursor.getColumnIndexOrThrow(PARAMETER_DESCRIPTION);
        int forecastIdIndex = cursor.getColumnIndexOrThrow(FORECAST_ID);

        int id = cursor.getInt(idIndex);
        String suggestionDescription = cursor.getString(suggestionDescriptionIndex);
        String parameterDescription = cursor.getString(parameterDescriptionIdIndex);
        int forecastId = cursor.getInt(forecastIdIndex);

        return new Suggestion(
                id,
                suggestionDescription,
                parameterDescription,
                forecastId);
    }
}

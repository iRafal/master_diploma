package com.medvid.andrii.diplomawork.data.suggestion.source;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;
import com.medvid.andrii.diplomawork.data.suggestion.SuggestionTableContract;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SuggestionLocalDataSource implements SuggestionDataSourceContract  {

    private static SuggestionLocalDataSource INSTANCE = null;

    private ContentResolver mContentResolver;

    public static SuggestionLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new SuggestionLocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private SuggestionLocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        mContentResolver = contentResolver;
    }

    @Override
    public long saveSuggestion(@NonNull Suggestion suggestion) {
        checkNotNull(suggestion);
        ContentValues contentValues = SuggestionTableContract.getInstance().getContentValues(suggestion);
        Uri uri = mContentResolver.insert(SuggestionTableContract.buildUri(), contentValues);
        return  Long.valueOf(uri.getLastPathSegment());
    }

    @Override
    public int saveSuggestions(@NonNull List<Suggestion> suggestionList) {
        checkNotNull(suggestionList);

        if(suggestionList.isEmpty())    {
            return 0;
        }

        ContentValues contentValues = null;
        List<ContentValues> contentValuesList = new ArrayList<>(suggestionList.size());

        for(Suggestion suggestion : suggestionList) {
            contentValues = SuggestionTableContract.getInstance().getContentValues(suggestion);
            contentValuesList.add(contentValues);
        }

        int rowsAffected =
                mContentResolver.bulkInsert(
                        SuggestionTableContract.buildUri(),
                        contentValuesList.toArray(new ContentValues[contentValuesList.size()]));

        return rowsAffected;
    }

    @Override
    public void getSuggestion(@NonNull String id, @NonNull GetSuggestionCallback callback) {
        //  load data via Cursor Loader //TODO
        checkNotNull(id);
        checkNotNull(callback);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor cursor = mContentResolver.query(
                SuggestionTableContract.buildUriWith(id),
                SuggestionTableContract.getInstance().getColumns(),
                selection, selectionArgs, null);
        Suggestion suggestion = null;
        if (cursor != null && cursor.moveToFirst()) {
            suggestion = SuggestionTableContract.getInstance().getEntity(cursor);
            cursor.close();
        }
        callback.onSuggestionLoaded(suggestion);
    }

    @Override
    public void getSuggestions(@NonNull long forecastId, @NonNull GetSuggestionsCallback callback) {
        //  load data via Cursor Loader //TODO
        checkNotNull(callback);

        String selection = SuggestionTableContract.FORECAST_ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(forecastId) };

        Cursor cursor = mContentResolver.query(
                SuggestionTableContract.buildUri(),
                SuggestionTableContract.getInstance().getColumns(),
                selection, selectionArgs, null);

        Suggestion suggestion = null;
        List<Suggestion> suggestionList = new ArrayList<>();

        if (cursor == null || !cursor.moveToFirst())	{
            cursor.close();
            callback.onSuggestionsLoaded(suggestionList);
            return;
        }

        do {
            suggestion = SuggestionTableContract.getInstance().getEntity(cursor);
            suggestionList.add(suggestion);
        } while (cursor.moveToNext());

        cursor.close();
        callback.onSuggestionsLoaded(suggestionList);
    }

    @Override
    public void deleteSuggestion(@NonNull String id) {
        checkNotNull(id);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {id};

        mContentResolver.delete(SuggestionTableContract.buildUriWith(id), selection, selectionArgs);
    }

    @Override
    public void deleteAllSuggestions() {
        mContentResolver.delete(SuggestionTableContract.buildUri(), null, null);
    }

    @Override
    public void updateSuggestion(@NonNull Suggestion suggestion) {
        checkNotNull(suggestion);

        ContentValues values = SuggestionTableContract.getInstance().getContentValues(suggestion);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {  Long.toString(suggestion.getId())  };

        mContentResolver.update(SuggestionTableContract.buildUri(), values, selection, selectionArgs);
    }
}

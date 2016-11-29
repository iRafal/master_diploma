package com.medvid.andrii.diplomawork.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.forecast.ForecastTableContract;
import com.medvid.andrii.diplomawork.data.suggestion.SuggestionTableContract;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSampleTableContract;
import com.medvid.andrii.diplomawork.data.user.UserTableContract;

public class DiplomaContentProvider extends ContentProvider {

    private static final String UNKNOWN_URI = "Unknown uri: ";
    private static final String URI_SEPARATOR = "/";
    private static final String ANY_LENGTH_SYMBOLS_STRING = "*";
    private static final String ANY_LENGTH_DIGITS_STRING = "#";

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MasterDiplomaDbHelper mMasterDiplomaDbHelper;

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = TableDefinitionContract.CONTENT_AUTHORITY;

        // User
        matcher.addURI(authority,
                UserTableContract.TABLE_NAME,
                UserTableContract.CODE_USER);

        matcher.addURI(
                authority,
                UserTableContract.TABLE_NAME + URI_SEPARATOR + ANY_LENGTH_SYMBOLS_STRING,
                UserTableContract.CODE_USER_ITEM);

        // Training Sample
        matcher.addURI(authority,
                TrainingSampleTableContract.TABLE_NAME,
                TrainingSampleTableContract.CODE_TRAINING_SAMPLE);

        matcher.addURI(
                authority,
                TrainingSampleTableContract.TABLE_NAME + URI_SEPARATOR + ANY_LENGTH_SYMBOLS_STRING,
                TrainingSampleTableContract.CODE_TRAINING_SAMPLE_ITEM);

        // Suggestion
        matcher.addURI(authority,
                SuggestionTableContract.TABLE_NAME,
                SuggestionTableContract.CODE_SUGGESTION);

        matcher.addURI(
                authority,
                SuggestionTableContract.TABLE_NAME + URI_SEPARATOR + ANY_LENGTH_SYMBOLS_STRING,
                SuggestionTableContract.CODE_SUGGESTION_ITEM);

        // Forecast
        matcher.addURI(authority,
                ForecastTableContract.TABLE_NAME,
                ForecastTableContract.CODE_FORECAST);

        matcher.addURI(
                authority,
                ForecastTableContract.TABLE_NAME + URI_SEPARATOR + ANY_LENGTH_SYMBOLS_STRING,
                ForecastTableContract.CODE_FORECAST_ITEM);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mMasterDiplomaDbHelper = new MasterDiplomaDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        String tableName = getTableName(uri);

        Cursor returnCursor = mMasterDiplomaDbHelper.getReadableDatabase().query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            // User
            case UserTableContract.CODE_USER:
                return UserTableContract.CONTENT_USER_TYPE;
            case UserTableContract.CODE_USER_ITEM:
                return UserTableContract.CONTENT_USER_ITEM_TYPE;

            // Training sample
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE:
                return TrainingSampleTableContract.CONTENT_TRAINING_SAMPLE_TYPE;
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE_ITEM:
                return TrainingSampleTableContract.CONTENT_TRAINING_SAMPLE_ITEM_TYPE;

            // Suggestion
            case SuggestionTableContract.CODE_SUGGESTION:
                return SuggestionTableContract.CONTENT_SUGGESTION_TYPE;
            case SuggestionTableContract.CODE_SUGGESTION_ITEM:
                return SuggestionTableContract.CONTENT_SUGGESTION_ITEM_TYPE;

            // Forecast
            case ForecastTableContract.CODE_FORECAST:
                return ForecastTableContract.CONTENT_FORECAST_TYPE;
            case ForecastTableContract.CODE_FORECAST_ITEM:
                return ForecastTableContract.CONTENT_FORECAST_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        Uri returnUri = null;
        String tableName = getTableName(uri);

        SQLiteDatabase db = mMasterDiplomaDbHelper.getWritableDatabase();
        long id = db.replace(tableName, null, contentValues);

        getContext().getContentResolver().notifyChange(uri, null);

        switch (sUriMatcher.match(uri)) {
            // User
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                returnUri = UserTableContract.buildUriWith(id);
                break;

            // Training sample
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE:
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE_ITEM:
                returnUri = TrainingSampleTableContract.buildUriWith(id);
                break;

            // Suggestion
            case SuggestionTableContract.CODE_SUGGESTION:
            case SuggestionTableContract.CODE_SUGGESTION_ITEM:
                returnUri = SuggestionTableContract.buildUriWith(id);
                break;

            // Forecast
            case ForecastTableContract.CODE_FORECAST:
            case ForecastTableContract.CODE_FORECAST_ITEM:
                returnUri = ForecastTableContract.buildUriWith(id);
                break;
        }

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String tableName = getTableName(uri);

        final SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();
        int rowsDeleted = database.delete(tableName, selection, selectionArgs);

        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        String tableName = getTableName(uri);

        SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(tableName, contentValues, selection, selectionArgs);

        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {

        String tableName = getTableName(uri);

        SQLiteDatabase db = mMasterDiplomaDbHelper.getWritableDatabase();
        db.beginTransaction();
        int retval = 0;

        try {
            for (ContentValues val : values) {
                db.replace(tableName, null, val);
            }

            db.setTransactionSuccessful();
            retval = values.length;
        } finally {
            db.endTransaction();
        }

        return retval;
    }

    private String getTableName(@NonNull Uri uri)   throws UnsupportedOperationException    {
        Preconditions.checkNotNull(uri);

        String tableName = null;

        switch (sUriMatcher.match(uri)) {
            // User
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                tableName = UserTableContract.TABLE_NAME;
                break;

            // Training sample
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE:
            case TrainingSampleTableContract.CODE_TRAINING_SAMPLE_ITEM:
                tableName = TrainingSampleTableContract.TABLE_NAME;
                break;

            // Suggestion
            case SuggestionTableContract.CODE_SUGGESTION:
            case SuggestionTableContract.CODE_SUGGESTION_ITEM:
                tableName = SuggestionTableContract.TABLE_NAME;
                break;

            // Forecast
            case ForecastTableContract.CODE_FORECAST:
            case ForecastTableContract.CODE_FORECAST_ITEM:
                tableName = ForecastTableContract.TABLE_NAME;
                break;

            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }
        return tableName;
    }
}

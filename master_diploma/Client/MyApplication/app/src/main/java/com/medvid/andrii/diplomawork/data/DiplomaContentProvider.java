package com.medvid.andrii.diplomawork.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

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

        matcher.addURI(authority,
                UserTableContract.TABLE_NAME,
                UserTableContract.CODE_USER);

        matcher.addURI(
                authority,
                UserTableContract.TABLE_NAME + URI_SEPARATOR + ANY_LENGTH_SYMBOLS_STRING,
                UserTableContract.CODE_USER_ITEM);

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

        String tableName = null;

        switch (sUriMatcher.match(uri)) {
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                tableName = UserTableContract.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

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
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case UserTableContract.CODE_USER:
                return UserTableContract.CONTENT_USER_TYPE;
            case UserTableContract.CODE_USER_ITEM:
                return UserTableContract.CONTENT_USER_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
        String tableName = null;

        switch (match) {
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                tableName = UserTableContract.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        SQLiteDatabase db = mMasterDiplomaDbHelper.getWritableDatabase();
        long id = db.replace(tableName, null, contentValues);

        getContext().getContentResolver().notifyChange(uri, null);

        switch (match) {
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                returnUri = UserTableContract.buildUriWith(id);
                break;
        }

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        String tableName = null;

        switch (match) {
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                tableName = UserTableContract.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        final SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();
        int rowsDeleted = database.delete(tableName, selection, selectionArgs);

        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        String tableName = null;

        switch (match) {
            case UserTableContract.CODE_USER:
            case UserTableContract.CODE_USER_ITEM:
                tableName = UserTableContract.TABLE_NAME;
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(tableName, contentValues, selection, selectionArgs);

        if (rowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}

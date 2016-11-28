package com.medvid.andrii.diplomawork.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.medvid.andrii.diplomawork.data.user.UserTableContract;

public class DiplomaContentProvider extends ContentProvider {

    private static final String UNKNOWN_URI = "Unknown uri: ";
    private static final String URI_SEPARATOR = "/";

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
                UserTableContract.TABLE_NAME + URI_SEPARATOR + "*",
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

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        String where = null;

        switch (sUriMatcher.match(uri)) {
            case UserTableContract.CODE_USER:
                sqLiteQueryBuilder.setTables(UserTableContract.TABLE_NAME);
            case UserTableContract.CODE_USER_ITEM:
                where = appendWhere(
                        where,
                        null,
                        UserTableContract._ID + "=" + ContentUris.parseId(uri));
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();

        Cursor returnCursor = sqLiteQueryBuilder.query(
                database,
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

        switch (match) {
            case UserTableContract.CODE_USER:
                break;
            case UserTableContract.CODE_USER_ITEM:
                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        String tableName = "";

        switch (match) {
            case UserTableContract.CODE_USER:
                tableName = UserTableContract.TABLE_NAME;
            case UserTableContract.CODE_USER_ITEM:
                selection = appendSelection(
                        selection,
                        UserTableContract._ID + "= ?" + ContentUris.parseId(uri));

                break;
            default:
                throw new UnsupportedOperationException(UNKNOWN_URI + uri);
        }

        final SQLiteDatabase database = mMasterDiplomaDbHelper.getWritableDatabase();
        database.beginTransaction();
        int rowsDeleted = 0;

        try {
            rowsDeleted = database.delete(tableName, selection, selectionArgs);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }

        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        String tableName = "";

        switch (match) {
            case UserTableContract.CODE_USER:
                tableName = UserTableContract.TABLE_NAME;
            case UserTableContract.CODE_USER_ITEM:
                selection = appendSelection(
                        selection,
                        UserTableContract._ID + "=" + ContentUris.parseId(uri));
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

    private String appendSelection(@Nullable String original, @NonNull String selection) {
        String newSelection =  TextUtils.isEmpty(original) ? "" : original + " AND ";
        newSelection += "(" + selection + ")";
        return newSelection;
    }

    private String appendWhere(String where, String conjuction, String clause) {
        String retval = "";

        if (!TextUtils.isEmpty(where)) {
            retval = where;

            if (!TextUtils.isEmpty(conjuction)) {
                retval += " " + conjuction + " ";
            } else {
                retval += " AND ";
            }
        }

        retval += "(" + clause + ")";

        return retval;
    }

}

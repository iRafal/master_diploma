    package com.medvid.andrii.diplomawork.data.user;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class UsersLocalDataSource implements UserDataSourceContract {

    private static UsersLocalDataSource INSTANCE = null;

    private ContentResolver mContentResolver;

    public static UsersLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new UsersLocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private UsersLocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        mContentResolver = contentResolver;
    }

    /**
     * {@link UserDataSourceContract } methods implementation
     */

    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        ContentValues contentValues = UserTableContract.getInstance().getContentValues(user);
        mContentResolver.insert(UserTableContract.buildUri(), contentValues);
    }

    @Override
    public void getUser(@NonNull String userId, @NonNull GetUserCallback callback) {
        //  data is loaded via Cursor Loader
    }

    @Override
    public void getUsers(@NonNull GetUsersCallback callback) {
        //  data is loaded via Cursor Loader
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        checkNotNull(userId);
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {userId};

        mContentResolver.delete(UserTableContract.buildUri(), selection, selectionArgs);
    }

    @Override
    public void deleteAllUsers() {
        mContentResolver.delete(UserTableContract.buildUri(), null, null);
    }

    @Override
    public void updateUser(@NonNull User user) {
        checkNotNull(user);

        ContentValues values = UserTableContract.getInstance().getContentValues(user);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {  Long.toString(user.getId())  };

        mContentResolver.update(UserTableContract.buildUri(), values, selection, selectionArgs);
    }
}

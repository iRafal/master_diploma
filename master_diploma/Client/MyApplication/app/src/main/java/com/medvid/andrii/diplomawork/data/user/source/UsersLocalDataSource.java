    package com.medvid.andrii.diplomawork.data.user.source;

    import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

    import com.medvid.andrii.diplomawork.data.user.User;
    import com.medvid.andrii.diplomawork.data.user.UserTableContract;

    import java.util.ArrayList;
import java.util.List;

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
        //  load data via Cursor Loader //TODO
        checkNotNull(userId);
        checkNotNull(callback);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {userId};

        Cursor cursor = mContentResolver.query(
                UserTableContract.buildUriWith(userId),
                UserTableContract.getInstance().getColumns(),
                selection, selectionArgs, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = UserTableContract.getInstance().getEntity(cursor);
            cursor.close();
        }
        callback.onUserLoaded(user);
    }

    @Override
    public void getUsers(@NonNull GetUsersCallback callback) {
        //  load data via Cursor Loader //TODO
        checkNotNull(callback);

        Cursor cursor = mContentResolver.query(
                UserTableContract.buildUri(),
                UserTableContract.getInstance().getColumns(),
                null, null, null);

        User user = null;
        List<User> userList = new ArrayList<>();

        if (cursor == null)	{
            callback.onUsersLoaded(userList);
        }
        if (!cursor.moveToFirst())	{
            callback.onUsersLoaded(userList);
        }

        do {
            user = UserTableContract.getInstance().getEntity(cursor);
            userList.add(user);
        } while (cursor.moveToNext());

        cursor.close();
        callback.onUsersLoaded(userList);
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        checkNotNull(userId);
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {userId};

        mContentResolver.delete(UserTableContract.buildUriWith(userId), selection, selectionArgs);
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

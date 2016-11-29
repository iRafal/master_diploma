package com.medvid.andrii.diplomawork.data.user.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.user.User;

import java.util.ArrayList;

public class UsersRemoteDataSource implements UserDataSourceContract {

    private static UsersRemoteDataSource INSTANCE = null;

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersRemoteDataSource();
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */

    private UsersRemoteDataSource() {

    }

    /**
     * {@link UserDataSourceContract } methods implementation
     */

    @Override
    public void saveUser(@NonNull User user) {
        // No need to save user on remote data source
    }

    @Override
    public void getUser(@NonNull String userId, @NonNull GetUserCallback callback) {
        callback.onUserLoaded(null); //TODO finish implementation
    }

    @Override
    public void getUsers(@NonNull GetUsersCallback callback) {
        callback.onUsersLoaded(new ArrayList<User>());
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        // No need to remove user from remote data source
    }

    @Override
    public void deleteAllUsers() {
        // No need to remove user(s) from remote data source
    }

    @Override
    public void updateUser(@NonNull User user) {
        Preconditions.checkNotNull(user);
    }
}

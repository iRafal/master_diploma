package com.medvid.andrii.diplomawork.data.user;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */

public interface UserDataSourceContract {

    interface GetUsersCallback {

        void onUsersLoaded(@NonNull List<User> users);

        void onDataNotAvailable();
    }

    interface GetUserCallback {

        void onUserLoaded(@NonNull User user);

        void onDataNotAvailable();
    }

    void saveUser(@NonNull User user);

    void getUser(@NonNull String userId, @NonNull GetUserCallback callback);

    void getUsers(@NonNull GetUsersCallback callback);

    void deleteUser(@NonNull String userId);

    void deleteAllUsers();

    void updateUser(@NonNull User user);

}

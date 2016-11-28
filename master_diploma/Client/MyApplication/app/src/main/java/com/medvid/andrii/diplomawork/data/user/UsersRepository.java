package com.medvid.andrii.diplomawork.data.user;

import android.support.annotation.NonNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class UsersRepository implements UserDataSourceContract {

    private static UsersRepository INSTANCE = null;

    private final UserDataSourceContract mUsersRemoteDataSource;
    private final UserDataSourceContract mUsersLocalDataSource;

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param userRemoteDataSource the backend data source
     * @param userLocalDataSource  the device storage data source
     * @return the {@link UsersRepository} instance
     */
    public static UsersRepository getInstance(@NonNull UserDataSourceContract userRemoteDataSource,
                                              @NonNull UserDataSourceContract userLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(userRemoteDataSource, userLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(UserDataSourceContract, UserDataSourceContract)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private UsersRepository(
            @NonNull UserDataSourceContract userRemoteDataSource,
            @NonNull UserDataSourceContract userLocalDataSource) {

        mUsersRemoteDataSource = checkNotNull(userRemoteDataSource);
        mUsersLocalDataSource = checkNotNull(userLocalDataSource);
    }

    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        mUsersRemoteDataSource.saveUser(user);
        mUsersLocalDataSource.saveUser(user);
    }

    @Override
    public void getUser(@NonNull String userId, @NonNull final GetUserCallback callback) {
        checkNotNull(userId);
        checkNotNull(callback);

        // Load from server
        mUsersRemoteDataSource.getUser(userId, new GetUserCallback() {

            @Override
            public void onUserLoaded(@NonNull User user) {
                callback.onUserLoaded(user);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUsers(@NonNull final GetUsersCallback callback) {
        checkNotNull(callback);

        // Load from server
        mUsersRemoteDataSource.getUsers(new GetUsersCallback() {

            @Override
            public void onUsersLoaded(@NonNull List<User> users) {
                refreshLocalDataSource(users);
                callback.onUsersLoaded(null);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void deleteUser(@NonNull String userId) {
        checkNotNull(userId);
        mUsersRemoteDataSource.deleteUser(userId);
        mUsersLocalDataSource.deleteUser(userId);
    }

    @Override
    public void deleteAllUsers() {
        mUsersRemoteDataSource.deleteAllUsers();
        mUsersLocalDataSource.deleteAllUsers();
    }

    @Override
    public void updateUser(@NonNull User user) {
        checkNotNull(user);
        mUsersRemoteDataSource.updateUser(user);
        mUsersLocalDataSource.updateUser(user);
    }

    private void refreshLocalDataSource(List<User> users) {
        for (User user : users) {
            mUsersLocalDataSource.saveUser(user);
        }
    }
}

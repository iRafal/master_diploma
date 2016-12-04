package com.medvid.andrii.diplomawork.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.forecast.source.ForecastLocalDataSource;
import com.medvid.andrii.diplomawork.data.suggestion.source.SuggestionLocalDataSource;
import com.medvid.andrii.diplomawork.data.training_sample.source.TrainingSamplesLocalDataSource;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.data.user.source.UserDataSourceContract;
import com.medvid.andrii.diplomawork.data.user.source.UsersLocalDataSource;
import com.medvid.andrii.diplomawork.forecasts.ForecastsCookies;

import java.util.List;

public class AccountManager {

    private AccountManager getInstance(@NonNull Context context)    {
        return new AccountManager(context);
    }

    private Context mContext;
    private UsersLocalDataSource mUsersLocalDataSource;
    private TrainingSamplesLocalDataSource mTrainingSamplesLocalDataSource;
    private SuggestionLocalDataSource mSuggestionLocalDataSource;
    private ForecastLocalDataSource mForecastLocalDataSource;


    public AccountManager(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        mContext = context;
        mUsersLocalDataSource = UsersLocalDataSource.getInstance(context.getContentResolver());
        mTrainingSamplesLocalDataSource = TrainingSamplesLocalDataSource.getInstance(context.getContentResolver());
        mSuggestionLocalDataSource = SuggestionLocalDataSource.getInstance(context.getContentResolver());
        mForecastLocalDataSource = ForecastLocalDataSource.getInstance(context.getContentResolver());
    }

    public void saveUser(@NonNull User user)  {
        Preconditions.checkNotNull(user);
        mUsersLocalDataSource.saveUser(user);
    }

    public void updateUserData(@NonNull User user)    {
        Preconditions.checkNotNull(user);
        mUsersLocalDataSource.updateUser(user);
    }

    /**
     * Remove user and all his data
     */
    public void logoutUser()   {
        mUsersLocalDataSource.deleteAllUsers();
        mTrainingSamplesLocalDataSource.deleteAllTrainingSamples();
        mSuggestionLocalDataSource.deleteAllSuggestions();
        mForecastLocalDataSource.deleteAllForecastSamples();
        ForecastsCookies.removeSavedTime();
    }

    /**
     * As only one user is logged in, gets list of users with one user object.
     * @return
     */
    public User getUserData()   {
        final ObjectWrapper<User> wrapper = new ObjectWrapper<>(null);
        mUsersLocalDataSource.getUsers(new UserDataSourceContract.GetUsersCallback() {
            @Override
            public void onUsersLoaded(@NonNull List<User> users) {
                if(users.isEmpty()) {
                    return;
                }
                wrapper.setWrapperValue(users.get(0));
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return wrapper.getWrapperValue();
    }
}

package com.medvid.andrii.diplomawork.profile;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.AccountManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfilePresenter implements ProfileContract.Presenter    {

    private final ProfileContract.View mView;

    public ProfilePresenter(@NonNull ProfileContract.View view) {
        mView = checkNotNull(view, ProfileContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public User getUserData() {
        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        return accountManager.getUserData();
    }
}

package com.medvid.andrii.diplomawork.settings;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.util.AccountManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class SettingsPresenter implements SettingsContract.Presenter    {

    private final SettingsContract.View mView;

    public SettingsPresenter(@NonNull SettingsContract.View view) {
        mView = checkNotNull(view, SettingsContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void performUserLogout() {
        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        accountManager.logoutUser();
    }

    @Override
    public void performLogout() {
        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        accountManager.logoutUser();
    }
}

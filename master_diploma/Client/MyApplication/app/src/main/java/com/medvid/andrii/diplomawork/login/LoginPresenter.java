package com.medvid.andrii.diplomawork.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = checkNotNull(loginView, LoginContract.View.class.getSimpleName() + " cannot be null");
        mLoginView.setPresenter(this);
    }

    @Override
    public boolean isLoginValid(@Nullable String login) {
        return false;
    }

    @Override
    public boolean isPasswordValid(@Nullable String password) {
        return false;
    }

    @Override
    public boolean isLoginDataValid() {
        return false;
    }

    @Override
    public void performLogin() {
        // TODO
    }
}

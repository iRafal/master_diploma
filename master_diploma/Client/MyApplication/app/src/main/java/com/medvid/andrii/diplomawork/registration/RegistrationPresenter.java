package com.medvid.andrii.diplomawork.registration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private final RegistrationContract.View mRegistrationView;

    public RegistrationPresenter(@NonNull RegistrationContract.View registrationView) {
        mRegistrationView = checkNotNull(registrationView, RegistrationContract.View.class.getSimpleName() + " cannot be null");
        mRegistrationView.setPresenter(this);
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
    public boolean isFirstNameValid(@Nullable String firstName) {
        return false;
    }

    @Override
    public boolean isLastNameValid(@Nullable String lastName) {
        return false;
    }

    @Override
    public void performRegistration() {
        // TODO
    }
}

package com.medvid.andrii.diplomawork.registration;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private final RegistrationContract.View mView;

    public RegistrationPresenter(@NonNull RegistrationContract.View view) {
        mView = checkNotNull(view, RegistrationContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public boolean isLoginValid(String login) {
        if(TextUtils.isEmpty(login))    {
            mView.showLoginError(StringUtils.getEmptyFieldString(R.string.login_camel_style));
            return false;
        }

        if(!login.contains("@"))    {
            mView.showLoginError(StringUtils.getWrongFieldString(R.string.login_camel_style));
            return false;
        }

        mView.hideLoginError();
        return true;
    }

    @Override
    public boolean isPasswordValid(String password) {
        if(TextUtils.isEmpty(password))    {
            mView.showPasswordError(StringUtils.getEmptyFieldString(R.string.password_camel_style));
            return false;
        }

        mView.hidePasswordError();
        return true;
    }

    @Override
    public boolean isConfirmPasswordValid(String password, String confirmPassword) {
        if(TextUtils.isEmpty(confirmPassword))    {
            mView.showConfirmPasswordError(StringUtils.getEmptyFieldString(R.string.confirm_password));
            return false;
        }

        if(!confirmPassword.equals(password))    {
            mView.showConfirmPasswordError(DiplomaApplication.getInstance().getString(R.string.passwords_do_not_match));
            return false;
        }

        mView.hideConfirmPasswordError();
        return true;
    }

    @Override
    public boolean checkRegistrationValid() {
        return isLoginValid(mView.getLogin())
                && isFirstNameValid(mView.getFirstName())
                && isLastNameValid(mView.getLastName())
                && isPasswordValid(mView.getPassword())
                && isConfirmPasswordValid(mView.getPassword(), mView.getConfirmPassword());
    }

    @Override
    public boolean isFirstNameValid(String firstName) {
        if(TextUtils.isEmpty(firstName))    {
            mView.showFirstNameError(true);
            return false;
        }

        mView.showFirstNameError(false);
        return true;
    }

    @Override
    public boolean isLastNameValid(String lastName) {
        if(TextUtils.isEmpty(lastName))    {
            mView.showLastNameError(true);
            return false;
        }

        mView.showLastNameError(false);
        return true;
    }

    @Override
    public void performRegistration() {
        if(!checkRegistrationValid()) {
            return;
        }
        mView.showHomeScreen();
    }
}

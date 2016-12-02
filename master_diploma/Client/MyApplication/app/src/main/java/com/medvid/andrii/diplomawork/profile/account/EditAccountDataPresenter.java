package com.medvid.andrii.diplomawork.profile.account;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.AccountManager;
import com.medvid.andrii.diplomawork.util.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditAccountDataPresenter implements EditAccountDataContract.Presenter {

    private final EditAccountDataContract.View mView;

    public EditAccountDataPresenter(@NonNull EditAccountDataContract.View view) {
        mView = checkNotNull(view, EditAccountDataContract.View.class.getSimpleName() + " cannot be null");
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
    public boolean checkUserDataValid() {
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
    public void updateUserInfo() {
        if(!checkUserDataValid()) {
            return;
        }

        User user = new User(0, mView.getLogin(), mView.getFirstName(), mView.getLastName(),
                0, User.Gender.MAN, 0, 0, 0);

        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        accountManager.updateUserData(user);

        mView.showProgressDialog(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mView != null) {
                    mView.finish();
                }
            }
        }, 1_500);
    }

    @Override
    public void setUserData() {
        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        User user = checkNotNull(accountManager.getUserData());

        mView.setLogin(user.getEmail());
        mView.setFirstName(user.getFirstName());
        mView.setLastName(user.getLastName());
    }
}

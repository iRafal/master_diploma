package com.medvid.andrii.diplomawork.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.AccountManager;
import com.medvid.andrii.diplomawork.util.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;

    public LoginPresenter(@NonNull LoginContract.View view) {
        mView = checkNotNull(view, LoginContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public boolean isLoginValid(String login) {
        if(TextUtils.isEmpty(login)) {
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
        if(TextUtils.isEmpty(password)) {
            mView.showPasswordError(StringUtils.getEmptyFieldString(R.string.password_camel_style));
            return false;
        }

        mView.hidePasswordError();
        return true;
    }

    @Override
    public boolean isLoginDataValid() {
        return isLoginValid(mView.getLogin()) && isPasswordValid(mView.getPassword());
    }

    @Override
    public void performLogin() {
        if(!isLoginDataValid()) {
            return;
        }

        User user = new User(0, mView.getLogin(), "Andrii", "Medvid", 0, User.Gender.MAN, 0, 0, 0);

        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        accountManager.logoutUser(); // Remove everything from DB.
        accountManager.saveUser(user);

        mView.showHomeScreen();
    }
}

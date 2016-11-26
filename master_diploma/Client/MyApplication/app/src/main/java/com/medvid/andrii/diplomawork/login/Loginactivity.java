package com.medvid.andrii.diplomawork.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends AppCompatActivity {

    private LoginContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context)    {
        checkNotNull(context);
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.contentFrame);
        }

        mPresenter = new LoginPresenter(loginFragment);
    }
}

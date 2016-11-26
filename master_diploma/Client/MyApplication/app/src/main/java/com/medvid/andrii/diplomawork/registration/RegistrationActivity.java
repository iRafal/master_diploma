package com.medvid.andrii.diplomawork.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context)    {
        checkNotNull(context);
        return new Intent(context, RegistrationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    private void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RegistrationFragment registrationFragment =
                (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (registrationFragment == null) {
            registrationFragment = RegistrationFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), registrationFragment, R.id.contentFrame);
        }

        mPresenter = new RegistrationPresenter(registrationFragment);
    }
}

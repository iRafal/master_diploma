package com.medvid.andrii.diplomawork.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.medvid.andrii.diplomawork.HomeActivity;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.login.LoginActivity;
import com.medvid.andrii.diplomawork.util.AccountManager;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandingActivity extends AppCompatActivity {

    private LandingContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context)    {
        checkNotNull(context);
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(performLogin())  {
            finish();
            return;
        }
        setContentView(R.layout.activity_landing);
        initViews();
    }

    private void initViews() {
        LandingFragment landingFragment =
                (LandingFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (landingFragment == null) {
            landingFragment = LandingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), landingFragment, R.id.contentFrame);
        }

        mPresenter = new LandingPresenter(landingFragment);
    }

    /**
     *
     * @return operation success
     */
    private boolean performLogin() {
        AccountManager accountManager = new AccountManager(this);
        User user = accountManager.getUserData();
        if(user == null)    {
            return false;
        }
        showHomeScreen();
        return true;
    }

    private void showHomeScreen()   {
        Intent intent = HomeActivity.getIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

package com.medvid.andrii.diplomawork.forecasts.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.forecasts.ForecastsFragment;

public class ForecastItemActivity extends AppCompatActivity {

    private FrameLayout mContentFrame;

    public static Intent getIntent(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        return new Intent(context, ForecastItemActivity.class);
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_item);
        initUi();
    }

    /**
     * Private methods
     */

    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContentFrame = (FrameLayout) findViewById(R.id.contentFrame);
        showFragment(ForecastsFragment.newInstance());
    }

    private void showFragment(@NonNull Fragment fragment) {

        if (isFinishing() || isDestroyed()) {
            return;
        }

        Preconditions.checkNotNull(fragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, fragment);
        fragmentTransaction.commit();
    }
}

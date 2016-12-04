package com.medvid.andrii.diplomawork.forecasts.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

public class ForecastItemActivity extends AppCompatActivity {

    public static final String KEY_FORECAST_ID
            = "com.medvid.andrii.diplomawork.forecasts.item.KEY_FORECAST_ID";

    private ForecastItemPresenter mPresenter;

    public static Intent getIntent(@NonNull Context context, long forecastId) {
        Preconditions.checkNotNull(context);
        Intent intent = new Intent(context, ForecastItemActivity.class);
        intent.putExtra(KEY_FORECAST_ID, forecastId);
        return intent;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /**
     * Private methods
     */

    private void initUi() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ForecastItemFragment fragment =
                (ForecastItemFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = ForecastItemFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        mPresenter = new ForecastItemPresenter(fragment);
    }
}

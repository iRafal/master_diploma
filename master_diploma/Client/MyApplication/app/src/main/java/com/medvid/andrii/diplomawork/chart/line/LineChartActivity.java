package com.medvid.andrii.diplomawork.chart.line;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

public class LineChartActivity  extends AppCompatActivity {

    public static final String KEY_PARAMETER
            = "com.medvid.andrii.diplomawork.chart.line.KEY_PARAMETER";

    private FrameLayout mContentFrame;
    private LineChartContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context, String parameter) {
        Preconditions.checkNotNull(context);
        Intent intent = new Intent(context, LineChartActivity.class);
        intent.putExtra(KEY_PARAMETER, parameter);
        return intent;
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
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

        mContentFrame = (FrameLayout) findViewById(R.id.contentFrame);

        LineChartFragment fragment =
                (LineChartFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = LineChartFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        mPresenter = new LineChartPresenter(fragment);
    }
}

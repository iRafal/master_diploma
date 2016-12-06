package com.medvid.andrii.diplomawork.tasks.item;

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

public class TaskItemActivity extends AppCompatActivity {

    public static final String TRAINING_SAMPLE_ID
            = "com.medvid.andrii.diplomawork.tasks.item.TRAINING_SAMPLE_ID";

    private FrameLayout mContentFrame;
    private TaskItemContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context, long trainindSampleId) {
        Preconditions.checkNotNull(context);
        Intent intent = new Intent(context, TaskItemActivity.class);
        intent.putExtra(TRAINING_SAMPLE_ID, trainindSampleId);
        return intent;
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_item);
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

        TaskItemFragment fragment =
                (TaskItemFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = TaskItemFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        mPresenter = new TaskItemPresenter(fragment);
    }
}

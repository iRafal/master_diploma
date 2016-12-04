package com.medvid.andrii.diplomawork.profile.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditAccountDataActivity extends AppCompatActivity {

    private EditAccountDataContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context)    {
        checkNotNull(context);
        return new Intent(context, EditAccountDataActivity.class);
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_account_edit);
        initViews();
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

    private void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditAccountDataFragment fragment =
                (EditAccountDataFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = EditAccountDataFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        mPresenter = new EditAccountDataPresenter(fragment);
    }
}

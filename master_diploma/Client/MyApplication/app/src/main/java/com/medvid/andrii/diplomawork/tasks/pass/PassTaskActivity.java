package com.medvid.andrii.diplomawork.tasks.pass;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.medvid.andrii.diplomawork.util.DialogUtils;

public class PassTaskActivity extends AppCompatActivity {

    private FrameLayout mContentFrame;
    private PassTaskContract.Presenter mPresenter;

    public static Intent getIntent(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        Intent intent = new Intent(context, PassTaskActivity.class);
        return intent;
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pass);
        initUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            showExitDialog();
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

        PassTaskFragment fragment =
                (PassTaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = PassTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        mPresenter = new PassTaskPresenter(fragment);
    }

    private void showExitDialog()   {

        DialogInterface.OnClickListener confirmClickAction = new  DialogInterface.OnClickListener()    {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        };

        Dialog logoutDialog = DialogUtils.createDialog(
                this,
                getString(R.string.exit),
                getString(R.string.exit_message),
                getString(R.string.ok),
                getString(R.string.cancel),
                confirmClickAction,
                null,
                false);
        logoutDialog.show();
    }
}

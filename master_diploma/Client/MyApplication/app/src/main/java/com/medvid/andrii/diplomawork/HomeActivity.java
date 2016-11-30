package com.medvid.andrii.diplomawork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.tasks.TasksFragment;
import com.medvid.andrii.diplomawork.profile.ProfileFragment;
import com.medvid.andrii.diplomawork.settings.SettingsFragment;
import com.medvid.andrii.diplomawork.forecasts.ForecastsFragment;

import java.lang.annotation.Retention;

import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.TASKS_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.PROFILE_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.SETTINGS_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.FORECASTS_TAB;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Container for:
 * TODO List of screens below
 */
public class HomeActivity extends AppCompatActivity {

    @Retention(SOURCE)
    @IntDef({TASKS_TAB, FORECASTS_TAB, PROFILE_TAB, SETTINGS_TAB})
    public @interface ScreenNames {
        int TASKS_TAB = 0;
        int FORECASTS_TAB = 1;
        int PROFILE_TAB = 2;
        int SETTINGS_TAB = 3;
    }

    private FrameLayout mContentFrame;
    private TabLayout mTabLayout;
    private TextView mToolbarTitle;

    public static Intent getIntent(@NonNull Context context, @ScreenNames int screenName) {
        Preconditions.checkNotNull(context);
        return new Intent(context, HomeActivity.class);
    }

    public static Intent getIntent(@NonNull Context context) {
        return getIntent(context, TASKS_TAB);
    }

    /**
     * Life Cycle methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUi();
    }

    /**
     * Private methods
     */

    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mToolbarTitle = (TextView) findViewById(R.id.title);
        mContentFrame = (FrameLayout) findViewById(R.id.contentFrame);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (mTabLayout.getSelectedTabPosition()) {
                    case TASKS_TAB:
                        mToolbarTitle.setText(R.string.tasks_camel_style);
                        showFragment(TasksFragment.newInstance());
                        break;
                    case FORECASTS_TAB:
                        mToolbarTitle.setText(R.string.forecasts_camel_style);
                        showFragment(ForecastsFragment.newInstance());
                        break;
                    case PROFILE_TAB:
                        mToolbarTitle.setText(R.string.profile_camel_style);
                        showFragment(ProfileFragment.newInstance());
                        break;
                    case SETTINGS_TAB:
                        mToolbarTitle.setText(R.string.settings_camel_style);
                        showFragment(SettingsFragment.newInstance());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mTabLayout.getTabAt(PROFILE_TAB).select();
    }

    private void showFragment(@NonNull Fragment fragment) {

        if(isFinishing() || isDestroyed())  {
            return;
        }

        Preconditions.checkNotNull(fragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame, fragment);
        fragmentTransaction.commit();
    }
}

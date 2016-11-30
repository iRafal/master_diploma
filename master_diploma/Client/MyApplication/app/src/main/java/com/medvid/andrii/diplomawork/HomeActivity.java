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

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.tasks.TasksFragment;
import com.medvid.andrii.diplomawork.profile.ProfileFragment;
import com.medvid.andrii.diplomawork.settings.SettingsFragment;
import com.medvid.andrii.diplomawork.forecasts.ForecastsFragment;

import java.lang.annotation.Retention;

import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.TASKS_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.PROFILE_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.SETTINGS_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.STATS_TAB;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Container for:
 * TODO List of screens below
 */
public class HomeActivity extends AppCompatActivity {

    @Retention(SOURCE)
    @IntDef({TASKS_TAB, STATS_TAB, PROFILE_TAB, SETTINGS_TAB})
    public @interface ScreenNames {
        int TASKS_TAB = 0;
        int STATS_TAB = 1;
        int PROFILE_TAB = 2;
        int SETTINGS_TAB = 3;
    }

    private FrameLayout mContentFrame;
    private TabLayout mTabLayout;

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

        mContentFrame = (FrameLayout) findViewById(R.id.contentFrame);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (mTabLayout.getSelectedTabPosition()) {
                    case TASKS_TAB:
                        showFragment(TasksFragment.newInstance());
                        break;
                    case STATS_TAB:
                        showFragment(ForecastsFragment.newInstance());
                        break;
                    case PROFILE_TAB:
                        showFragment(ProfileFragment.newInstance());
                        break;
                    case SETTINGS_TAB:
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

        TabLayout.Tab tab = mTabLayout.getTabAt(TASKS_TAB);
        tab.select();
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

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.common.base.Preconditions;
import com.mindmarker.mindmarker.mindmarker.list.MindmarkersListFragment;
import com.mindmarker.mindmarker.programs.info.ProgramInfoFragment;

import java.lang.annotation.Retention;

import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.INFO_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.LEADERBOARS_LIST_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.MINDMARKERS_LIST_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.PROFILE_LIST;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.PROFILE_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.PROGRAM_INFO_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.RESOURCES_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.SETTINGS_TAB;
import static com.medvid.andrii.diplomawork.HomeActivity.ScreenNames.STATS_TAB;
import static com.mindmarker.mindmarker.HomeActivity.ScreenNames.LEADERBOARS_LIST_TAB;
import static com.mindmarker.mindmarker.HomeActivity.ScreenNames.MINDMARKERS_LIST_TAB;
import static com.mindmarker.mindmarker.HomeActivity.ScreenNames.PROGRAM_INFO_TAB;
import static com.mindmarker.mindmarker.HomeActivity.ScreenNames.RESOURCES_TAB;
import static com.mindmarker.mindmarker.HomeActivity.ScreenNames.STATS_TAB;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Container for:
 * TODO List of screens below
 */
public class HomeActivity extends AppCompatActivity {

    @Retention(SOURCE)
    @IntDef({INFO_TAB, STATS_TAB, PROFILE_TAB, SETTINGS_TAB})
    public @interface ScreenNames {
        int INFO_TAB = 0;
        int STATS_TAB =1;
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
        return getIntent(context, INFO_TAB);
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
                    case PROGRAM_INFO_TAB:
                        showFragment(ProgramInfoFragment.newInstance());
                        break;
                    case RESOURCES_TAB:
                        break;
                    case MINDMARKERS_LIST_TAB:
                        showFragment(MindmarkersListFragment.newInstance());
                        break;
                    case LEADERBOARS_LIST_TAB:
                        break;
                    case STATS_TAB:
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

        TabLayout.Tab tab = mTabLayout.getTabAt(INFO_TAB);
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

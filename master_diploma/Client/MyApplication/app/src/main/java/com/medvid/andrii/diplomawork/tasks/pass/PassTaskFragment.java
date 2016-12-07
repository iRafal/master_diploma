package com.medvid.andrii.diplomawork.tasks.pass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;

import java.util.LinkedList;
import java.util.List;

public class PassTaskFragment extends Fragment implements PassTaskContract.View,
        FoodMultiplicityLayout.OnItemValueChangedListener {

    private ScrollView mScrollView;

    private TextInputLayout mTemperatureTextInputLayout;
    private EditText mTemperatureEditText;

    private TextInputLayout mStressLevelTextInputLayout;
    private EditText mStressLevelEditText;

    private TextInputLayout mHighPressureTextInputLayout;
    private EditText mHighPressureEditText;

    private TextInputLayout mLowPressureTextInputLayout;
    private EditText mLowPressureEditText;

    private TextInputLayout mPulseTextInputLayout;
    private EditText mPulseEditText;

    private TextInputLayout mSleepHoursTextInputLayout;
    private EditText mSleepHoursEditText;

    private TextInputLayout mSleepQuantityTextInputLayout;
    private EditText mSleepQuantityEditText;

    private TextInputLayout mDistanceTextInputLayout;
    private EditText mDistanceEditText;

    private TextInputLayout mSportActivityTimeTextInputLayout;
    private EditText mSportActivityTimeEditText;

    private TextView mFoodMultiplicityLabel;

    private FoodMultiplicityLayout mFoodMultiplicityLayout;

    private LinearLayout foodMultiplicityItemsContainer;

    private List<TextInputLayout> mTextInputLayouts = new LinkedList<>();
    private List<EditText> mEditTexts = new LinkedList<>();

    private ProgressDialog mProgressDialog;

    private LayoutInflater mLayoutInflater;

    private Handler mHandler = new Handler();

    private PassTaskContract.Presenter mPresenter;

    public static PassTaskFragment newInstance() {
        return new PassTaskFragment();
    }

    public PassTaskFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_pass, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.pass_task_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                showProgressDialog(true);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1_500);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutInflater = getActivity().getLayoutInflater();
        initUi(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * {@link PassTaskContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(PassTaskContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public String getTemperature() {
        return mTemperatureEditText.getText().toString();
    }

    @Override
    public String getStressLevel() {
        return mStressLevelEditText.getText().toString();
    }

    @Override
    public String getHighPressure() {
        return mHighPressureEditText.getText().toString();
    }

    @Override
    public String getLowPressure() {
        return mLowPressureEditText.getText().toString();
    }

    @Override
    public String getPulse() {
        return mPulseEditText.getText().toString();
    }

    @Override
    public String getSleepHours() {
        return mSleepHoursEditText.getText().toString();
    }

    @Override
    public String getSleepQuantity() {
        return mSleepQuantityEditText.getText().toString();
    }

    @Override
    public String getDistance() {
        return mDistanceEditText.getText().toString();
    }

    @Override
    public String getSportActivityTime() {
        return mSportActivityTimeEditText.getText().toString();
    }

    @Override
    public int getFoodMultiplicity() {
        return mFoodMultiplicityLayout.getValue();
    }

    @Override
    public List<String> getEatenKilocalories() {
        // TODO
        return null;
    }

    @Override
    public void showTemperatureError() {
        mTemperatureTextInputLayout.setError("");
    }

    @Override
    public void showStressLevelError() {
        mStressLevelTextInputLayout.setError("");
    }

    @Override
    public void showHighPressureError() {
        mHighPressureTextInputLayout.setError("");
    }

    @Override
    public void showLowPressureError() {
        mLowPressureTextInputLayout.setError("");
    }

    @Override
    public void showPulseError() {
        mPulseTextInputLayout.setError("");
    }

    @Override
    public void showSleepHoursError() {
        mSleepHoursTextInputLayout.setError("");
    }

    @Override
    public void showSleepQuantityError() {
        mSleepQuantityTextInputLayout.setError("");
    }

    @Override
    public void showDistanceError() {
        mDistanceTextInputLayout.setError("");
    }

    @Override
    public void showSportActivityTimeError() {
        mSportActivityTimeTextInputLayout.setError("");
    }

    @Override
    public void showEatenKilocaloriesError(int position) {
        // TODO
    }

    @Override
    public void hideTemperatureError() {
        mTemperatureTextInputLayout.setError("");
    }

    @Override
    public void hideStressLevelError() {
        mStressLevelTextInputLayout.setError("");
    }

    @Override
    public void hideHighPressureError() {
        mHighPressureTextInputLayout.setError("");
    }

    @Override
    public void hideLowPressureError() {
        mLowPressureTextInputLayout.setError("");
    }

    @Override
    public void hidePulseError() {
        mPulseTextInputLayout.setError("");
    }

    @Override
    public void hideSleepHoursError() {
        mSleepQuantityTextInputLayout.setError("");
    }

    @Override
    public void hideSleepQuantityError() {
        mSleepQuantityTextInputLayout.setError("");
    }

    @Override
    public void hideDistanceError() {
        mDistanceTextInputLayout.setError("");
    }

    @Override
    public void hideSportActivityTimeError() {
        mSportActivityTimeTextInputLayout.setError("");
    }

    @Override
    public void hideEatenKilocaloriesError(int position) {
        // TODO
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void showProgressDialog(boolean show) {

        if (show) {
            mProgressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.saving), true);
            return;
        }

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * {@link FoodMultiplicityLayout.OnItemValueChangedListener}
     */

    @Override
    public void onDecreasedValue(int value) {
        int layoutChildrenCount = foodMultiplicityItemsContainer.getChildCount();

        if (layoutChildrenCount == 0) {
            return;
        }

        foodMultiplicityItemsContainer.removeViewAt(layoutChildrenCount - 1);

        mTextInputLayouts.remove(mTextInputLayouts.size() - 1);
        mEditTexts.remove(mEditTexts.size() - 1);
    }

    @Override
    public void onIncreasedValue(int value) {
        View view = mLayoutInflater.inflate(R.layout.food_multiplicity_item, null);
        foodMultiplicityItemsContainer.addView(view);

        mTextInputLayouts.add(
                (TextInputLayout) view.findViewById(R.id.foodMultiplicityTextInputLayout));

        mEditTexts.add(
                (EditText) view.findViewById(R.id.foodMultiplicityEditText));

        scrollToBottom();
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mScrollView = (ScrollView) rootView.findViewById(R.id.scrollView);

        mTemperatureTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.temperatureTextInputLayout);
        mTemperatureEditText
                = (EditText) rootView.findViewById(R.id.temperatureEditText);

        mStressLevelTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.stressLevelTextInputLayout);
        mStressLevelEditText
                = (EditText) rootView.findViewById(R.id.stressLevelEditText);

        mHighPressureTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.highPressureTextInputLayout);
        mHighPressureEditText
                = (EditText) rootView.findViewById(R.id.highPressureEditText);

        mLowPressureTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.lowPressureTextInputLayout);
        mLowPressureEditText
                = (EditText) rootView.findViewById(R.id.lowPressureEditText);

        mPulseTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.pulseTextInputLayout);
        mPulseEditText
                = (EditText) rootView.findViewById(R.id.pulseEditText);

        mSleepHoursTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.sleepHoursTextInputLayout);
        mSleepHoursEditText
                = (EditText) rootView.findViewById(R.id.sleepHoursEditText);

        mSleepQuantityTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.sleepQuantityTextInputLayout);
        mSleepQuantityEditText
                = (EditText) rootView.findViewById(R.id.sleepQuantityEditText);

        mDistanceTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.distanceTextInputLayout);
        mDistanceEditText
                = (EditText) rootView.findViewById(R.id.distanceEditText);

        mSportActivityTimeTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.sportActivityTimeTextInputLayout);
        mSportActivityTimeEditText
                = (EditText) rootView.findViewById(R.id.sportActivityTimeEditText);

        mFoodMultiplicityLabel
                = (TextView) rootView.findViewById(R.id.foodMultiplicityLabel);

        mFoodMultiplicityLayout
                = (FoodMultiplicityLayout) rootView.findViewById(R.id.valueChangeWidget);
        mFoodMultiplicityLayout.setListener(this);

        foodMultiplicityItemsContainer
                = (LinearLayout) rootView.findViewById(R.id.foodMultiplicityItemsContainer);
    }

    private void scrollToBottom() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mScrollView != null) {
                    mScrollView.smoothScrollBy(0, mScrollView.getBottom());
                }
            }
        }, 200);
    }
}

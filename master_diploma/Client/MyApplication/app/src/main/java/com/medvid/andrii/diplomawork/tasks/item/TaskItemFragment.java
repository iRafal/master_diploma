package com.medvid.andrii.diplomawork.tasks.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;

public class TaskItemFragment extends Fragment implements TaskItemContract.View {

    private TextView mTemperatureTextView;
    private TextView mStressLevelTextView;
    private TextView mHighPressureTextView;
    private TextView mLowPressureTextView;
    private TextView mPulseTextView;
    private TextView mSleepHoursTextView;
    private TextView mSleepQuantityTextView;
    private TextView mDistanceHoursTextView;
    private TextView mFoodMultiplicityTextView;
    private TextView mSpentCaloriesTextView;
    private TextView mEatenCaloriesTextView;

    private TaskItemContract.Presenter mPresenter;

    public static TaskItemFragment newInstance() {
        return new TaskItemFragment();
    }

    public TaskItemFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

        long trainingSampleId = getTrainingSampleId();
        mPresenter.setTrainingSessionDataOnUi(trainingSampleId);
    }

    @Override
    public void setTemperature(double temperature) {
        mTemperatureTextView.setText(String.format("%s C", temperature));
    }

    @Override
    public void setStressLevel(double stressLevel) {
        mStressLevelTextView.setText(
                String.format("%s %s", stressLevel, getString(R.string.points)));
    }

    @Override
    public void setHighPressure(double highPressure) {
        mHighPressureTextView.setText(
                String.format("%s %s", highPressure, getString(R.string.mm_hg)));
    }

    @Override
    public void setLowPressure(double lowPressure) {
        mLowPressureTextView.setText(
                String.format("%s %s", lowPressure, getString(R.string.mm_hg)));
    }

    @Override
    public void setPulse(double pulse) {
        mPulseTextView.setText(
                String.format("%s %s", pulse, getString(R.string.b_p_m)));
    }

    @Override
    public void setSleepHours(double sleepHours) {
        mSleepHoursTextView.setText(
                String.format("%s %s", sleepHours, getString(R.string.hour_s)));
    }

    @Override
    public void setSleepQuantity(double sleepQuantity) {
        mSleepQuantityTextView.setText(
                String.format("%s %s", sleepQuantity, getString(R.string.points)));
    }

    @Override
    public void setDistance(double distance) {
        mDistanceHoursTextView.setText(
                String.format("%s %s", distance, getString(R.string.km_s)));
    }

    @Override
    public void setFoodMultiplicity(double foodMultiplicity) {
        mFoodMultiplicityTextView.setText(
                String.format("%s %s", foodMultiplicity, getString(R.string.time_s)));
    }

    @Override
    public void setSpentCalories(double spentCalories) {
        mSpentCaloriesTextView.setText(
                String.format("%s %s", spentCalories, getString(R.string.kilocalories)));
    }

    @Override
    public void setEatenCalories(double eatenCalories) {
        mEatenCaloriesTextView.setText(
                String.format("%s %s", eatenCalories, getString(R.string.kilocalories)));
    }

    @Override
    public void setTitle(String title) {
        ((TextView)getActivity().findViewById(R.id.title)).setText(title);
    }

    /**
     * {@link TaskItemContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(TaskItemContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mTemperatureTextView = (TextView) rootView.findViewById(R.id.temperatureTextView);
        mStressLevelTextView = (TextView) rootView.findViewById(R.id.stressLevelTextView);
        mHighPressureTextView = (TextView) rootView.findViewById(R.id.highPressureTextView);
        mLowPressureTextView = (TextView) rootView.findViewById(R.id.lowPressureTextView);
        mPulseTextView = (TextView) rootView.findViewById(R.id.pulseTextView);
        mSleepHoursTextView = (TextView) rootView.findViewById(R.id.sleepHoursTextView);
        mSleepQuantityTextView = (TextView) rootView.findViewById(R.id.sleepQuantityTextView);
        mDistanceHoursTextView = (TextView) rootView.findViewById(R.id.distanceTextView);
        mFoodMultiplicityTextView = (TextView) rootView.findViewById(R.id.foodMultiplicityTextView);
        mSpentCaloriesTextView = (TextView) rootView.findViewById(R.id.spentCaloriesTextView);
        mEatenCaloriesTextView = (TextView) rootView.findViewById(R.id.eatenCaloriesTextView);
    }

    /**
     * @return -1 if fail
     */
    private long getTrainingSampleId() {
        Intent intent = getActivity().getIntent();

        if (intent != null
                && intent.getExtras() != null
                && intent.getExtras().containsKey(TaskItemActivity.TRAINING_SAMPLE_ID)) {
            return intent.getExtras().getLong(TaskItemActivity.TRAINING_SAMPLE_ID);
        }
        return -1;
    }
}

package com.medvid.andrii.diplomawork.tasks.item;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;
import com.medvid.andrii.diplomawork.data.training_sample.source.TrainingSampleDataSourceContract;
import com.medvid.andrii.diplomawork.data.training_sample.source.TrainingSamplesLocalDataSource;
import com.medvid.andrii.diplomawork.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaskItemPresenter implements TaskItemContract.Presenter {

    private final TaskItemContract.View mView;

    public TaskItemPresenter(@NonNull TaskItemContract.View view) {
        mView = checkNotNull(view, TaskItemContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void setTrainingSessionDataOnUi(long trainingSessionId) {

        TrainingSamplesLocalDataSource source
                = TrainingSamplesLocalDataSource.getInstance(DiplomaApplication.getInstance().getContentResolver());

        source.getTrainingSample(Long.toString(trainingSessionId), new TrainingSampleDataSourceContract.GetTrainingSampleCallback() {
            @Override
            public void onTrainingSampleLoaded(@NonNull TrainingSample trainingSample) {
                mView.setTemperature(Math.round(trainingSample.getTemperature()));
                mView.setStressLevel(Math.round(trainingSample.getStressLevel()));
                mView.setHighPressure(Math.round(trainingSample.getPressure().getHighPressure()));
                mView.setLowPressure(Math.round(trainingSample.getPressure().getLowPressure()));
                mView.setPulse(Math.round(trainingSample.getPulse()));
                mView.setSleepHours(Math.round(trainingSample.getSleep().getSleepHoursCount()));
                mView.setSleepQuantity(Math.round(trainingSample.getSleep().getSleepQuality()));
                mView.setDistance(Math.round(trainingSample.getDistance()));
                mView.setFoodMultiplicity(Math.round(trainingSample.getFoodMultiplicity()));
                mView.setSpentCalories(Math.round(trainingSample.getCalories().getSpentCalories()));
                mView.setEatenCalories(Math.round(trainingSample.getCalories().getEatenCalories()));

                SimpleDateFormat simpleDateFormat = Utils.getDateFormatterByLocale(
                        "MM/dd/yyyy", Locale.getDefault());

                mView.setTitle(simpleDateFormat.format(trainingSample.getTimeStamp()));
            }

            @Override
            public void onDataNotAvailable() {
                mView.setTemperature(0);
                mView.setStressLevel(0);
                mView.setHighPressure(0);
                mView.setLowPressure(0);
                mView.setPulse(0);
                mView.setSleepHours(0);
                mView.setSleepQuantity(0);
                mView.setDistance(0);
                mView.setFoodMultiplicity(0);
                mView.setSpentCalories(0);
                mView.setEatenCalories(0);

                mView.setTitle(DiplomaApplication.getInstance().getString(R.string.date));
            }
        });
    }
}

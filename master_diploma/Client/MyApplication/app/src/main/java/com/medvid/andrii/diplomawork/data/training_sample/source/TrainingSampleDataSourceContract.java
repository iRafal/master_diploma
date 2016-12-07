package com.medvid.andrii.diplomawork.data.training_sample.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a pass task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */

public interface TrainingSampleDataSourceContract {

    interface GetTrainingSamplesCallback {

        void onTrainingSamplesLoaded(@NonNull List<TrainingSample> trainingSamples);

        void onDataNotAvailable();
    }

    interface GetTrainingSampleCallback {

        void onTrainingSampleLoaded(@NonNull TrainingSample trainingSample);

        void onDataNotAvailable();
    }

    void saveTrainingSample(@NonNull TrainingSample trainingSample);

    long saveTrainingSamples(@NonNull List<TrainingSample> list);

    void getTrainingSample(@NonNull String id, @NonNull GetTrainingSampleCallback callback);

    void getTrainingSamples(@NonNull GetTrainingSamplesCallback callback);

    void getForecastTrainingSamples(@NonNull GetTrainingSamplesCallback callback);

    void getStatisticsTrainingSamples(@NonNull GetTrainingSamplesCallback callback);

    void deleteTrainingSample(@NonNull String id);

    void deleteAllTrainingSamples();

    void updateTrainingSample(@NonNull TrainingSample trainingSample);

}

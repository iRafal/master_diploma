package com.medvid.andrii.diplomawork.data.training_sample.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;

public class TrainingSampleRemoteDataSource implements TrainingSampleDataSourceContract {

    private static TrainingSampleRemoteDataSource INSTANCE = null;

    public static TrainingSampleRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrainingSampleRemoteDataSource();
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */

    private TrainingSampleRemoteDataSource() {

    }

    /**
     * {@link TrainingSampleDataSourceContract } methods implementation
     */

    @Override
    public void saveTrainingSample(@NonNull TrainingSample trainingSample) {

    }

    @Override
    public void getTrainingSample(@NonNull String id, @NonNull GetTrainingSampleCallback callback) {
        callback.onTrainingSampleLoaded(null); //TODO finish implementation
    }

    @Override
    public void getTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {
        callback.onTrainingSamplesLoaded(null); //TODO finish implementation
    }

    @Override
    public void deleteTrainingSample(@NonNull String id) {

    }

    @Override
    public void deleteAllTrainingSamples() {

    }

    @Override
    public void updateTrainingSample(@NonNull TrainingSample trainingSample) {

    }
}

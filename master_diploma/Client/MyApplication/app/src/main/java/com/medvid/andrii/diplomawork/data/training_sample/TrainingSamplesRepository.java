package com.medvid.andrii.diplomawork.data.training_sample;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class TrainingSamplesRepository implements TrainingSampleDataSourceContract {

    private static TrainingSamplesRepository INSTANCE = null;

    private final TrainingSampleDataSourceContract mTrainingSampleRemoteDataSource;
    private final TrainingSampleDataSourceContract mTrainingSampleLocalDataSource;

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param trainingSampleRemoteDataSource the backend data source
     * @param trainingSampleLocalDataSource  the device storage data source
     * @return the {@link TrainingSamplesRepository} instance
     */
    public static TrainingSamplesRepository getInstance(@NonNull TrainingSampleDataSourceContract trainingSampleRemoteDataSource,
                                              @NonNull TrainingSampleDataSourceContract trainingSampleLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TrainingSamplesRepository(trainingSampleRemoteDataSource, trainingSampleLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(TrainingSampleDataSourceContract, TrainingSampleDataSourceContract)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private TrainingSamplesRepository(
            @NonNull TrainingSampleDataSourceContract trainingSampleRemoteDataSource,
            @NonNull TrainingSampleDataSourceContract trainingSampleLocalDataSource) {

        mTrainingSampleRemoteDataSource = checkNotNull(trainingSampleRemoteDataSource);
        mTrainingSampleLocalDataSource = checkNotNull(trainingSampleLocalDataSource);
    }

    @Override
    public void saveTrainingSample(@NonNull TrainingSample trainingSample) {

    }

    @Override
    public void getTrainingSample(@NonNull String id, @NonNull GetTrainingSampleCallback callback) {

    }

    @Override
    public void getTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {

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

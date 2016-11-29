package com.medvid.andrii.diplomawork.data.forecast;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */

public interface ForecastDataSourceContract {

    interface GetForecastSamplesCallback {

        void onForecastsLoaded(@NonNull List<Forecast> forecasts);

        void onDataNotAvailable();
    }

    interface GetForecastSampleCallback {

        void onForecastSampleLoaded(@NonNull Forecast forecast);

        void onDataNotAvailable();
    }

    void saveForecastSample(@NonNull Forecast forecast);

    void getForecastSample(@NonNull String id, @NonNull GetForecastSampleCallback callback);

    void getForecastSamples(@NonNull GetForecastSamplesCallback callback);

    void deleteForecastSample(@NonNull String id);

    void deleteAllForecastSamples();

    void updateForecastSample(@NonNull Forecast forecast);

}

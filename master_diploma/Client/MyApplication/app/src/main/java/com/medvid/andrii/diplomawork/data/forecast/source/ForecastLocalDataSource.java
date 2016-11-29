package com.medvid.andrii.diplomawork.data.forecast.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.forecast.Forecast;

public class ForecastLocalDataSource implements ForecastDataSourceContract  {
    @Override
    public void saveForecastSample(@NonNull Forecast forecast) {

    }

    @Override
    public void getForecastSample(@NonNull String id, @NonNull GetForecastSampleCallback callback) {
        //  load data via Cursor Loader //TODO
    }

    @Override
    public void getForecastSamples(@NonNull GetForecastSamplesCallback callback) {
        //  load data via Cursor Loader //TODO
    }

    @Override
    public void deleteForecastSample(@NonNull String id) {

    }

    @Override
    public void deleteAllForecastSamples() {

    }

    @Override
    public void updateForecastSample(@NonNull Forecast forecast) {

    }
}

package com.medvid.andrii.diplomawork.data.forecast.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.forecast.Forecast;

public class ForecastRemotelDataSource implements ForecastDataSourceContract  {

    @Override
    public long saveForecastSample(@NonNull Forecast forecast) {
        return 0;
    }

    @Override
    public void getForecastSample(@NonNull String id, @NonNull GetForecastSampleCallback callback) {
    }

    @Override
    public void getForecastSamples(@NonNull GetForecastSamplesCallback callback) {
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

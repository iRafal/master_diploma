package com.medvid.andrii.diplomawork.data.forecast;

import android.support.annotation.NonNull;

public class ForecastRemotelDataSource implements ForecastDataSourceContract  {
    @Override
    public void saveForecastSample(@NonNull Forecast forecast) {
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

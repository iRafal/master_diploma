package com.medvid.andrii.diplomawork.forecasts;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ForecastsPresenter implements ForecastsContract.Presenter    {

    private final ForecastsContract.View mView;

    public ForecastsPresenter(@NonNull ForecastsContract.View view) {
        mView = checkNotNull(view, ForecastsContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

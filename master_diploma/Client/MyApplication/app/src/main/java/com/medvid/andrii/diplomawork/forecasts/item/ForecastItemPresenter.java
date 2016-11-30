package com.medvid.andrii.diplomawork.forecasts.item;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ForecastItemPresenter implements ForecastItemContract.Presenter    {

    private final ForecastItemContract.View mView;

    public ForecastItemPresenter(@NonNull ForecastItemContract.View view) {
        mView = checkNotNull(view, ForecastItemContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

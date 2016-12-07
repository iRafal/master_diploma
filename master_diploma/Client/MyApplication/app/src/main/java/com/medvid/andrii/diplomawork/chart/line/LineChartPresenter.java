package com.medvid.andrii.diplomawork.chart.line;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class LineChartPresenter implements LineChartContract.Presenter {

    private final LineChartContract.View mView;

    public LineChartPresenter(@NonNull LineChartContract.View view) {
        mView = checkNotNull(view, LineChartContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

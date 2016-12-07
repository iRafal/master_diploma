package com.medvid.andrii.diplomawork.chart.line;

import com.medvid.andrii.diplomawork.BasePresenter;
import com.medvid.andrii.diplomawork.BaseView;

public interface LineChartContract {

    interface View extends BaseView<LineChartContract.Presenter> {

        void setTitle(String title);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}

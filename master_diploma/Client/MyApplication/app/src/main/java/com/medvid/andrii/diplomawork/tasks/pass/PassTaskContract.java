/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.medvid.andrii.diplomawork.tasks.pass;

import com.medvid.andrii.diplomawork.BasePresenter;
import com.medvid.andrii.diplomawork.BaseView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface PassTaskContract {

    interface View extends BaseView<Presenter> {

        String getTemperature();
        String getStressLevel();
        String getHighPressure();
        String getLowPressure();
        String getPulse();
        String getSleepHours();
        String getSleepQuantity();
        String getDistance();
        String getSportActivityTime();
        int getFoodMultiplicity();
        List<String> getEatenKilocalories();

        void showTemperatureError();
        void showStressLevelError();
        void showHighPressureError();
        void showLowPressureError();
        void showPulseError();
        void showSleepHoursError();
        void showSleepQuantityError();
        void showDistanceError();
        void showSportActivityTimeError();
        void showEatenKilocaloriesError(int position);

        void hideTemperatureError();
        void hideStressLevelError();
        void hideHighPressureError();
        void hideLowPressureError();
        void hidePulseError();
        void hideSleepHoursError();
        void hideSleepQuantityError();
        void hideDistanceError();
        void hideSportActivityTimeError();
        void hideEatenKilocaloriesError(int position);

        void finish();

        void showProgressDialog(boolean show);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

    }
}

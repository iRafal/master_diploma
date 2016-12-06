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

package com.medvid.andrii.diplomawork.tasks.item;

import com.medvid.andrii.diplomawork.BasePresenter;
import com.medvid.andrii.diplomawork.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface TaskItemContract {

    interface View extends BaseView<Presenter> {

        void setTemperature(double temperature);

        void setStressLevel(double stressLevel);

        void setHighPressure(double highPressure);

        void setLowPressure(double lowPressure);

        void setPulse(double pulse);

        void setSleepHours(double sleepHours);

        void setSleepQuantity(double sleepQuantity);

        void setDistance(double distance);

        void setFoodMultiplicity(double foodMultiplicity);

        void setSpentCalories(double spentCalories);

        void setEatenCalories(double eatenCalories);

        void setTitle(String title);

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void setTrainingSessionDataOnUi(long trainingSessionId);

    }
}

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

package com.medvid.andrii.diplomawork.profile.data;

import com.medvid.andrii.diplomawork.BasePresenter;
import com.medvid.andrii.diplomawork.BaseView;
import com.medvid.andrii.diplomawork.data.user.User;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface EditProfileDataContract {

    interface View extends BaseView<Presenter> {

        @User.Gender int getGender();

        void setGender(@User.Gender int gender);

        double getAge();

        void setAge(double age);

        double getGrowth();

        void setGrowth(double growth);

        double getWeight();

        void setWeight(double weight);

        void showAgeError(boolean show);

        void showGrowthError(boolean show);

        void showWeightError(boolean show);

        void showNetworkError(boolean show);

        void showProgressDialog(boolean show);

        void finish();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        boolean isAgeValid(double age);

        boolean isGrowthValid(double growth);

        boolean isWeightValid(double weight);

        boolean isValidationPassed();

        void updateProfileData();

        /**
         * Get user data from DB and set on the Screen.
         */
        void setProfileDataOnUi();

    }
}

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

package com.medvid.andrii.diplomawork.registration;

import com.medvid.andrii.diplomawork.BasePresenter;
import com.medvid.andrii.diplomawork.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface RegistrationContract {

    interface View extends BaseView<Presenter> {

        String getLogin();

        String getFirstName();

        String getLastName();

        String getPassword();

        String getConfirmPassword();

        void showLoginError(String message);

        void hideLoginError();

        void showFirstNameError(boolean show);

        void showLastNameError(boolean show);

        void showPasswordError(String message);

        void hidePasswordError();

        void showConfirmPasswordError(String message);

        void hideConfirmPasswordError();

        void showNetworkError(boolean show);

        void showHomeScreen();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        /**
         * Login validation
         * @param login
         * @return operation success
         */
        boolean isLoginValid(String login);

        /**
         * Password validation
         * @param password
         * @return operation success
         */
        boolean isPasswordValid(String password);

        /**
         * Confirm password validation
         * @param password
         * @param confirmPassword
         * @return operation success
         */
        boolean isConfirmPasswordValid(String password, String confirmPassword);

        boolean checkRegistrationValid();

        boolean isFirstNameValid(String firstName);

        boolean isLastNameValid(String lastName);

        /**
         * Call Api Service
         */
        void performRegistration();

    }
}

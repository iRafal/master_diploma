package com.medvid.andrii.diplomawork.profile.data;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.AccountManager;
import com.medvid.andrii.diplomawork.util.Utils;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditProfileDataPresenter implements EditProfileDataContract.Presenter {

    private static final double MAX_AGE = 100;
    private static final double MIN_AGE = 15;

    private static final double MAX_GROWTH = 2.0;
    private static final double MIN_GROWTH = 1.0;

    private static final double MAX_WEIGHT = 100;
    private static final double MIN_WEIGHT = 15;

    private final EditProfileDataContract.View mView;

    public EditProfileDataPresenter(@NonNull EditProfileDataContract.View view) {
        mView = checkNotNull(view, EditProfileDataContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public boolean isAgeValid(double age) {
        boolean isValid = (age >= MIN_AGE && age <= MAX_AGE);
        mView.showAgeError(!isValid);
        return isValid;
    }

    @Override
    public boolean isGrowthValid(double growth) {
        boolean isValid = (growth >= MIN_GROWTH && growth <= MAX_GROWTH);
        mView.showGrowthError(!isValid);
        return isValid;
    }

    @Override
    public boolean isWeightValid(double weight) {
        boolean isValid = (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT);
        mView.showWeightError(!isValid);
        return isValid;
    }

    @Override
    public boolean areCaloriesPerHourTrainingValid(double caloriesPerHourTraining) {
        boolean isValid = caloriesPerHourTraining != 0;
        mView.showCaloriesPerHourTrainingError(!isValid);
        return isValid;
    }

    @Override
    public boolean isValidationPassed() {
        return isAgeValid(mView.getAge())
                && isGrowthValid(mView.getGrowth())
                && isWeightValid(mView.getWeight())
                && areCaloriesPerHourTrainingValid(mView.getCaloriesPerHourTraining());
    }

    @Override
    public void updateProfileData() {

        if(!isValidationPassed())   {
            return;
        }

        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        User user = accountManager.getUserData();

        int gender = mView.getGender();
        double age = mView.getAge();
        double growth = mView.getGrowth();
        double weight = mView.getWeight();
        double bodyMassIndex = Utils.calculateBodyMassIndex(weight, growth);
        double caloriesPerHourTraining = mView.getCaloriesPerHourTraining();

        user.setGender(gender);
        user.setAge(age);
        user.setGrowth(growth);
        user.setWeight(weight);
        user.setBodyMassIndex(bodyMassIndex);
        user.setCaloriesPerHourTraining(caloriesPerHourTraining);

        accountManager.updateUserData(user);

        mView.showProgressDialog(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mView != null) {
                    mView.finish();
                }
            }
        }, 1_500);
    }

    @Override
    public void setProfileDataOnUi() {

        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        User user = accountManager.getUserData();

        mView.setAge(user.getAge());
        mView.setGender(user.getGender());
        mView.setGrowth(user.getGrowth());
        mView.setWeight(user.getWeight());
        mView.setCaloriesPerHourTraining(user.getCaloriesPerHourTraining());
    }
}

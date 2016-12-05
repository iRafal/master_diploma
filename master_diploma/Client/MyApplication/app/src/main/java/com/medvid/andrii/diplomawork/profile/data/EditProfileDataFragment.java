package com.medvid.andrii.diplomawork.profile.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.user.User;

/**
 * Use the {@link EditProfileDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EditProfileDataFragment extends Fragment implements EditProfileDataContract.View, View.OnClickListener {

    private TextView mGenderLabel;
    private RadioGroup mGenderRadioGroup;
    private RadioButton mFemaleRadioButton;
    private RadioButton mMaleRadioButton;

    private TextInputLayout mAgeTextInputLayout;
    private EditText mAgeEditText;

    private TextInputLayout mGrowthTextInputLayout;
    private EditText mGrowthEditText;

    private TextInputLayout mWeightTextInputLayout;
    private EditText mWeightEditText;

    private TextInputLayout mCaloriesPerHourTrainingTextInputLayout;
    private EditText mCaloriesPerHourTrainingEditText;

    private TextView mUpdateTextView;

    private ProgressDialog mProgressDialog;

    private EditProfileDataContract.Presenter mPresenter;

    public static EditProfileDataFragment newInstance() {
        return new EditProfileDataFragment();
    }

    public EditProfileDataFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_data_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
        mPresenter.setProfileDataOnUi();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /***
     * Listeners
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateTextView:
                mPresenter.updateProfileData();
                break;
        }
    }

    /**
     * {@link EditProfileDataContract.View} methods
     */

    @Override
    public int getGender() {
        return mMaleRadioButton.isChecked() ? User.Gender.MAN : User.Gender.WOMAN;
    }

    @Override
    public void setGender(@User.Gender int gender) {
        mFemaleRadioButton.setChecked(gender == User.Gender.WOMAN);
        mMaleRadioButton.setChecked(gender == User.Gender.MAN);
    }

    @Override
    public double getAge() {
        String text = mAgeEditText.getText().toString();
        return text.isEmpty() ? 0 : Double.parseDouble(text);
    }

    @Override
    public void setAge(double age) {
        mAgeEditText.setText(Double.toString(age));
    }

    @Override
    public double getGrowth() {
        String text = mGrowthEditText.getText().toString();
        return text.isEmpty() ? 0.0 : Double.parseDouble(text);
    }

    @Override
    public void setGrowth(double growth) {
        mGrowthEditText.setText(Double.toString(growth));
    }

    @Override
    public double getWeight() {
        String text = mWeightEditText.getText().toString();
        return text.isEmpty() ? 0.0 : Double.parseDouble(text);
    }

    @Override
    public void setCaloriesPerHourTraining(double caloriesPerHourTraining) {
        mCaloriesPerHourTrainingEditText.setText(Double.toString(caloriesPerHourTraining));
    }

    @Override
    public double getCaloriesPerHourTraining() {
        String text = mCaloriesPerHourTrainingEditText.getText().toString();
        return text.isEmpty() ? 0.0 : Double.parseDouble(text);
    }

    @Override
    public void setWeight(double weight) {
        mWeightEditText.setText(Double.toString(weight));
    }

    @Override
    public void showAgeError(boolean show) {
        mAgeTextInputLayout.setError(show ? getString(R.string.age_error_message) : "");
    }

    @Override
    public void showGrowthError(boolean show) {
        mGrowthTextInputLayout.setError(show ? getString(R.string.growth_error_message) : "");
    }

    @Override
    public void showWeightError(boolean show) {
        mWeightTextInputLayout.setError(show ? getString(R.string.weight_error_message) : "");
    }

    @Override
    public void showCaloriesPerHourTrainingError(boolean show) {
        mCaloriesPerHourTrainingTextInputLayout.setError(
                show ? getString(R.string.calories_per_hour_training_empty_error) : "");
    }

    @Override
    public void showNetworkError(boolean show) {
        // TODO
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void showProgressDialog(boolean show)  {

        if(show) {
            mProgressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.processing), true);
            return;
        }

        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(EditProfileDataContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {

        mGenderLabel = (TextView) rootView.findViewById(R.id.genderLabel);
        mGenderRadioGroup = (RadioGroup) rootView.findViewById(R.id.genderRadioGroup);
        mFemaleRadioButton = (RadioButton) rootView.findViewById(R.id.radioButtonFemale);
        mMaleRadioButton = (RadioButton) rootView.findViewById(R.id.radioButtonMale);

        mAgeTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.ageTextInputLayout);
        mAgeEditText = (EditText) rootView.findViewById(R.id.ageEditText);

        mGrowthTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.growthTextInputLayout);
        mGrowthEditText = (EditText) rootView.findViewById(R.id.growthEditText);

        mWeightTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.weightTextInputLayout);
        mWeightEditText = (EditText) rootView.findViewById(R.id.weightEditText);

        mCaloriesPerHourTrainingTextInputLayout
                = (TextInputLayout) rootView.findViewById(R.id.caloriesPerHourTrainingTextInputLayout);

        mCaloriesPerHourTrainingEditText
                = (EditText) rootView.findViewById(R.id.caloriesPerHourTrainingEditText);

        mUpdateTextView = (TextView) rootView.findViewById(R.id.updateTextView);
        mUpdateTextView.setOnClickListener(this);

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    mPresenter.performRegistration();
                }
                return false;
            }
        };

        mCaloriesPerHourTrainingEditText.setOnEditorActionListener(editorActionListener);
    }
}

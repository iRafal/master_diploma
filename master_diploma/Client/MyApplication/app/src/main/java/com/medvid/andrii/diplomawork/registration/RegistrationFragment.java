package com.medvid.andrii.diplomawork.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.medvid.andrii.diplomawork.HomeActivity;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.login.LoginContract;

/**
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment implements RegistrationContract.View, View.OnClickListener   {

    private RegistrationContract.Presenter mPresenter;

    private TextInputLayout mLoginTextInputLayout;
    private EditText mLoginEditText;

    private TextInputLayout mFirstNameTextInputLayout;
    private EditText mFirstNameEditText;

    private TextInputLayout mLastNameTextInputLayout;
    private EditText mLastNameEditText;

    private TextInputLayout mPassTextInputLayout;
    private EditText mPassEditText;

    private TextInputLayout mConfirmPasswordTextInputLayout;
    private EditText mConfirmPasswordEditText;

    private TextView mRegisterTextView;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    public RegistrationFragment() {
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
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
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
        switch (view.getId())   {
            case R.id.registerTextView:
                showHomeScreen();
                break;
        }
    }

    /**
     * {@link LoginContract.View} methods
     */
    @Override
    public void setPresenter(RegistrationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public String getLogin() {
        return mLoginEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassEditText.getText().toString();
    }

    @Override
    public String getFirstName() {
        return mFirstNameEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return mLastNameEditText.getText().toString();
    }

    @Override
    public void showLoginError() {
        mLoginTextInputLayout.setError(getString(R.string.wrong_login));
    }

    @Override
    public void showPasswordError() {
        mPassTextInputLayout.setError(getString(R.string.wrong_password));
    }

    @Override
    public void showFirstNameError() {
        mFirstNameTextInputLayout.setError(getString(R.string.first_name_empty));
    }

    @Override
    public void showLastNameError() {
        mLastNameTextInputLayout.setError(getString(R.string.last_name_empty));
    }

    @Override
    public void showNetworkError() {
        // TODO
    }

    @Override
    public void showHomeScreen() {
        getActivity().startActivity(HomeActivity.getIntent(getActivity()));
    }

    @Override
    public boolean isActive() {
        return isActive();
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView)   {

        mLoginTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.loginTextInputLayout);
        mLoginEditText = (EditText) rootView.findViewById(R.id.loginEditText);

        mFirstNameTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.firstNameTextInputLayout);
        mFirstNameEditText = (EditText) rootView.findViewById(R.id.firstNameEditText);

        mLastNameTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.lastNameTextInputLayout);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.lastNameEditText);

        mPassTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.passwordTextInputLayout);
        mPassEditText = (EditText) rootView.findViewById(R.id.passwordEditText);

        mConfirmPasswordTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.confirmPasswordTextInputLayout);
        mConfirmPasswordEditText = (EditText) rootView.findViewById(R.id.confirmPasswordEditText);

        mRegisterTextView = (TextView) rootView.findViewById(R.id.registerTextView);
        mRegisterTextView.setOnClickListener(this);
    }
}

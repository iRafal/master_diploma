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

    private TextInputLayout mPassTextInputLayout;
    private EditText mPassEditText;

    private TextView mLoginTextView;

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
        return inflater.inflate(R.layout.fragment_login, container, false);
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
            case R.id.loginTextView:
//                getActivity().startActivity(.getIntent(getActivity()));
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
        return null; //TODO
    }

    @Override
    public String getLastName() {
        return null; //TODO
    }

    @Override
    public void showLoginError() {
        mLoginTextInputLayout.setError("Wrong login");
    }

    @Override
    public void showPasswordError() {
        mPassTextInputLayout.setError("Wrong password");
    }

    @Override
    public void showFirstNameError() {
        // TODO
    }

    @Override
    public void showLastNameError() {
        // TODO
    }

    @Override
    public void showNetworkError() {
        // TODO
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

        mPassTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.passwordTextInputLayout);
        mPassEditText = (EditText) rootView.findViewById(R.id.passwordEditText);

        mLoginTextView = (TextView) rootView.findViewById(R.id.loginTextView);
        mLoginTextView.setOnClickListener(this);
    }
}

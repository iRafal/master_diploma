package com.medvid.andrii.diplomawork.profile.account;

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
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;

/**
 * Use the {@link EditAccountDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAccountDataFragment extends Fragment implements EditAccountDataContract.View, View.OnClickListener {

    private EditAccountDataContract.Presenter mPresenter;

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

    private TextView mUpdateTextView;

    public static EditAccountDataFragment newInstance() {
        return new EditAccountDataFragment();
    }

    public EditAccountDataFragment() {
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
        return inflater.inflate(R.layout.fragment_profile_account_edit, container, false);
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
            case R.id.updateTextView:
//                mPresenter.performRegistration();
                break;
        }
    }

    /**
     * {@link EditAccountDataContract.View} methods
     */

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(EditAccountDataContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
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

        TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    mPresenter.performRegistration();
                }
                return false;
            }
        };

        mConfirmPasswordEditText.setOnEditorActionListener(editorActionListener);

        mUpdateTextView = (TextView) rootView.findViewById(R.id.updateTextView);
        mUpdateTextView.setOnClickListener(this);
    }
}

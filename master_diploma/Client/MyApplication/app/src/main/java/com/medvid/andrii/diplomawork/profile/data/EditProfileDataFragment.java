package com.medvid.andrii.diplomawork.profile.data;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;

/**
 * Use the {@link EditProfileDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class EditProfileDataFragment extends Fragment implements EditProfileDataContract.View, View.OnClickListener {

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
//            case R.id.updateTextView:
//                mPresenter.performRegistration();
//                break;
        }
    }

    /**
     * {@link EditProfileDataContract.View} methods
     */

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(EditProfileDataContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
    
    }
}

package com.medvid.andrii.diplomawork.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.user.User;

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private TextView mUserName;
    private TextView mUserEmail;
    private ImageView mUserIcon;
    private View mUserAccountData;
    private View mUserProfileData;
    private ProfileContract.Presenter mPresenter;

    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserData();
    }

    /**
     * {@link ProfileContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Interfaces implementations
     */

    @Override
    public void onClick(View view) {
        switch (view.getId())   {
            case R.id.userAccountData:
                break;
            case R.id.userProfileData:
                break;
        }
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mUserName = (TextView) rootView.findViewById(R.id.userName);
        mUserEmail = (TextView) rootView.findViewById(R.id.userEmail);
        mUserIcon = (ImageView) rootView.findViewById(R.id.userIcon);

        mUserAccountData = rootView.findViewById(R.id.userAccountData);
        mUserProfileData = rootView.findViewById(R.id.userProfileData);
    }

    private void setUserData()  {
        User user = mPresenter.getUserData();
        Preconditions.checkNotNull(user);

        mUserEmail.setText(user.getEmail());
        mUserName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
    }

}

package com.medvid.andrii.diplomawork.profile;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfilePresenter implements ProfileContract.Presenter    {

    private final ProfileContract.View mView;

    public ProfilePresenter(@NonNull ProfileContract.View view) {
        mView = checkNotNull(view, ProfileContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

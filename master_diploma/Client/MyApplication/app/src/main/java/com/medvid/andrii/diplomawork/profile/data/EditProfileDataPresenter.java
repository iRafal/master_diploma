package com.medvid.andrii.diplomawork.profile.data;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditProfileDataPresenter implements EditProfileDataContract.Presenter {

    private final EditProfileDataContract.View mView;

    public EditProfileDataPresenter(@NonNull EditProfileDataContract.View view) {
        mView = checkNotNull(view, EditProfileDataContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

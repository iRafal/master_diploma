package com.medvid.andrii.diplomawork.profile.account;

import android.support.annotation.NonNull;


import static com.google.common.base.Preconditions.checkNotNull;

public class EditAccountDataPresenter implements EditAccountDataContract.Presenter {

    private final EditAccountDataContract.View mView;

    public EditAccountDataPresenter(@NonNull EditAccountDataContract.View view) {
        mView = checkNotNull(view, EditAccountDataContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

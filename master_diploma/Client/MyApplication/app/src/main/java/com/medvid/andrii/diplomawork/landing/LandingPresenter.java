package com.medvid.andrii.diplomawork.landing;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandingPresenter implements LandingContract.Presenter {

    private final LandingContract.View mView;

    public LandingPresenter(@NonNull LandingContract.View view) {
        mView = checkNotNull(view, LandingContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

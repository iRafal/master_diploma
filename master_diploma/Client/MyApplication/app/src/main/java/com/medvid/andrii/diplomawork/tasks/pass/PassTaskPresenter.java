package com.medvid.andrii.diplomawork.tasks.pass;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class PassTaskPresenter implements PassTaskContract.Presenter {

    private final PassTaskContract.View mView;

    public PassTaskPresenter(@NonNull PassTaskContract.View view) {
        mView = checkNotNull(view, PassTaskContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

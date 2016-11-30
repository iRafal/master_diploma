package com.medvid.andrii.diplomawork.tasks.item;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaskItemPresenter implements TaskItemContract.Presenter    {

    private final TaskItemContract.View mView;

    public TaskItemPresenter(@NonNull TaskItemContract.View view) {
        mView = checkNotNull(view, TaskItemContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

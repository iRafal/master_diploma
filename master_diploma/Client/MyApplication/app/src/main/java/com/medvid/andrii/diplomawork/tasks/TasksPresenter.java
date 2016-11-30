package com.medvid.andrii.diplomawork.tasks;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksPresenter implements TasksContract.Presenter    {

    private final TasksContract.View mView;

    public TasksPresenter(@NonNull TasksContract.View view) {
        mView = checkNotNull(view, TasksContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }
}

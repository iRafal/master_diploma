package com.medvid.andrii.diplomawork.tasks.item;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.tasks.TasksFragment;

public class TaskItemFragment extends Fragment implements TaskItemContract.View {

    private TaskItemContract.Presenter mPresenter;

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    public TaskItemFragment() {
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
        return inflater.inflate(R.layout.fragment_tasks_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

    }

    /**
     * {@link TaskItemContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(TaskItemContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);
    }
}

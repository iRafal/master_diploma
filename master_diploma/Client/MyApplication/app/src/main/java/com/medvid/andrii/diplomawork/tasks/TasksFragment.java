package com.medvid.andrii.diplomawork.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.tasks.item.TaskItemActivity;
import com.medvid.andrii.diplomawork.tasks.pass.PassTaskActivity;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.List;

public class TasksFragment extends Fragment
        implements TasksContract.View, OnListItemClickListener<Task> {

    private View mEmptyListLayout;
    private RecyclerView mRecyclerView;
    private TasksContract.Presenter mPresenter;
    private TasksListAdapter mTasksListAdapter;

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    public TasksFragment() {
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
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setTrainingSamplesFromLocalStorage();
    }

    /**
     * {@link TasksContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showTaskItemScreen(long trainingSampleId) {
        getActivity().startActivity(TaskItemActivity.getIntent(getActivity(), trainingSampleId));
    }

//    @Override
    public void showPassTaskScreen() {
        getActivity().startActivity(PassTaskActivity.getIntent(getActivity()));
    }

    @Override
    public void showListEmptyView(boolean show)    {
        mEmptyListLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void refreshList(@NonNull List<Task> list) {
        Preconditions.checkNotNull(list);

        if (mTasksListAdapter == null) {
            mTasksListAdapter = new TasksListAdapter(list, TasksFragment.this);
            mRecyclerView.setAdapter(mTasksListAdapter);
        } else {
            mTasksListAdapter.refresh(list);
        }
    }

    /**
     * {@link OnListItemClickListener}
     */

    @Override
    public void onListItemClick(@NonNull Task object) {
        Preconditions.checkNotNull(object);

        if(object.isPassed()) {
            long trainingSampleId = object.getTrainingSample().getId();
            showTaskItemScreen(trainingSampleId);
            return;
        }

        showPassTaskScreen();
    }

    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mEmptyListLayout = rootView.findViewById(R.id.emptyListLayout);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

}

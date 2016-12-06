package com.medvid.andrii.diplomawork.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;
import com.medvid.andrii.diplomawork.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {

    private List<Task> mItemsList;
    private OnListItemClickListener mOnItemClickListener;

    public TasksListAdapter(@NonNull List<Task> itemsList,
                            @NonNull OnListItemClickListener<Task> onItemClickListener) {

        mItemsList = Preconditions.checkNotNull(itemsList);
        mOnItemClickListener = Preconditions.checkNotNull(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public TasksListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.tasks_list_item, parent, false);

        return new TasksListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksListAdapter.ViewHolder viewHolder, final int position) {

        Context context = DiplomaApplication.getInstance();
        Task task = mItemsList.get(position);

        Date date = task.isPassed()
                ? task.getTrainingSample().getTimeStamp()
                : new Date();
        SimpleDateFormat simpleDateFormat = Utils.getDateFormatterByLocale(
                "MM/dd/yyyy", Locale.getDefault());

        viewHolder.dateTextView.setText(
                DiplomaApplication.getInstance()
                        .getString(R.string.date_is, simpleDateFormat.format(date)));

        viewHolder.taskStatusTextView.setText(
                task.isPassed()
                        ? DiplomaApplication.getInstance().getString(R.string.yes)
                        : DiplomaApplication.getInstance().getString(R.string.no));

        viewHolder.taskStatusTextView.setTextColor(
                task.isPassed()
                        ? ContextCompat.getColor(context, android.R.color.holo_green_dark)
                        : ContextCompat.getColor(context, android.R.color.holo_orange_light));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) {
                    return;
                }
                mOnItemClickListener.onListItemClick(mItemsList.get(position));
            }
        });
    }

    public void refresh(@NonNull List<Task> itemsList) {
        Preconditions.checkNotNull(itemsList);
        mItemsList = Preconditions.checkNotNull(itemsList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateTextView;
        public final TextView passedLabelTextView;
        public final TextView taskStatusTextView;
        public final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            this.passedLabelTextView = (TextView) itemView.findViewById(R.id.passedLabelTextView);
            this.taskStatusTextView = (TextView) itemView.findViewById(R.id.taskStatusTextView);
        }
    }
}
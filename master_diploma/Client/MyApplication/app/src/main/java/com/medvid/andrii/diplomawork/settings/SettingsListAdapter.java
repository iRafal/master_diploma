package com.medvid.andrii.diplomawork.settings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.List;

public class SettingsListAdapter extends RecyclerView.Adapter<SettingsListAdapter.ViewHolder> {

    private List<String> mItemsList;
    private OnListItemClickListener mOnItemClickListener;

    public SettingsListAdapter(@NonNull List<String> itemsList,
                               @NonNull OnListItemClickListener onItemClickListener) {

        Preconditions.checkNotNull(itemsList);
        Preconditions.checkNotNull(onItemClickListener);

        mItemsList = itemsList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItemsList == null ? 0 : mItemsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(mItemsList.get(position));
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener == null) {
                    return;
                }

                mOnItemClickListener.onListItemClick(mItemsList.get(position));
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

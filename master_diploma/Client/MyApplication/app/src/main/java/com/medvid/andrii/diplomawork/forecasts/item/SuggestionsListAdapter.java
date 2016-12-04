package com.medvid.andrii.diplomawork.forecasts.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.List;

public class SuggestionsListAdapter extends RecyclerView.Adapter<SuggestionsListAdapter.ViewHolder> {

    private List<Suggestion> mItemsList;
    private OnListItemClickListener mOnItemClickListener;

    public SuggestionsListAdapter(@NonNull List<Suggestion> itemsList,
                                  @NonNull OnListItemClickListener<Suggestion> onItemClickListener) {

        mItemsList = Preconditions.checkNotNull(itemsList);
        mOnItemClickListener = Preconditions.checkNotNull(onItemClickListener);
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
    public SuggestionsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.suggestions_list_item, parent, false);

        return new SuggestionsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuggestionsListAdapter.ViewHolder viewHolder, final int position) {
        Suggestion suggestion = mItemsList.get(position);

        viewHolder.parameterTextView.setText(suggestion.getParameterDescription());

        viewHolder.suggestionTextView.setText(suggestion.getSuggestionDescription());

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

    public void refresh(@NonNull List<Suggestion> itemsList) {
        Preconditions.checkNotNull(itemsList);
        mItemsList = Preconditions.checkNotNull(itemsList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView parameterTextView;
        public final TextView suggestionTextView;
        public final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.parameterTextView = (TextView) itemView.findViewById(R.id.parameterTextView);
            this.suggestionTextView = (TextView) itemView.findViewById(R.id.suggestionTextView);
        }
    }
}
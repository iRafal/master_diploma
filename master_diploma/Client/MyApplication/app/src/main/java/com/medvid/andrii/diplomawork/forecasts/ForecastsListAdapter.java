package com.medvid.andrii.diplomawork.forecasts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.data.forecast.Forecast;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.List;

public class ForecastsListAdapter extends RecyclerView.Adapter<ForecastsListAdapter.ViewHolder> {

    private List<Forecast> mItemsList;
    private OnListItemClickListener mOnItemClickListener;

    public ForecastsListAdapter(@NonNull List<Forecast> itemsList,
                                @NonNull OnListItemClickListener<Forecast> onItemClickListener) {

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.forecasts_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Forecast forecast = mItemsList.get(position);
        viewHolder.diseaseTextView.setText(
                DiplomaApplication.getInstance().getString(R.string.disease_risk_is,
                        forecast.getDisease().getDiseaseName()));

        viewHolder.groupRiskTextView.setText(
                DiplomaApplication.getInstance().getString(R.string.group_risk_is,
                        forecast.getGroupRisk().getRiskName()));

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

    public void refresh(@NonNull List<Forecast> itemsList) {
        Preconditions.checkNotNull(itemsList);
        mItemsList = Preconditions.checkNotNull(itemsList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView diseaseTextView;
        public final TextView groupRiskTextView;
        public final View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.diseaseTextView = (TextView) itemView.findViewById(R.id.diseaseTextView);
            this.groupRiskTextView = (TextView) itemView.findViewById(R.id.groupRiskTextView);
        }
    }
}

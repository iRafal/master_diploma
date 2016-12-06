package com.medvid.andrii.diplomawork.forecasts.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

public class ForecastItemFragment extends Fragment implements ForecastItemContract.View, OnListItemClickListener<Suggestion> {

    private View mEmptyListLayout;
    private TextView mDiseaseTextView;
    private RecyclerView mRecyclerView;
    private TextView mGroupRiskTextView;
    private TextView mSuggestionsTextView;
    private ForecastItemContract.Presenter mPresenter;
    private SuggestionsListAdapter mSuggestionsListAdapter;

    public static ForecastItemFragment newInstance() {
        return new ForecastItemFragment();
    }

    public ForecastItemFragment() {
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
        return inflater.inflate(R.layout.fragment_forecast_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

        long forecastId = getForecastId();
        mPresenter.setSuggestionsDataOnUi(forecastId);
        mPresenter.setForecastDataOnUi(forecastId);
    }

    @Override
    public void refreshList(@NonNull List<Suggestion> suggestionList) {
        Preconditions.checkNotNull(suggestionList);

        if (mSuggestionsListAdapter == null) {
            mSuggestionsListAdapter = new SuggestionsListAdapter(suggestionList, ForecastItemFragment.this);
            mRecyclerView.setAdapter(mSuggestionsListAdapter);
        } else {
            mSuggestionsListAdapter.refresh(suggestionList);
        }
    }

    @Override
    public void setDiseaseName(String disease) {
        mDiseaseTextView.setText(getString(R.string.disease_risk_is, disease));
    }

    @Override
    public void setGroupRiskName(String groupRiskName) {
        mGroupRiskTextView.setText(getString(R.string.group_risk_is, groupRiskName));
    }

    /**
     * {@link ForecastItemContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(ForecastItemContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showListEmptyView(boolean show)    {
        mEmptyListLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    /**
     *  {@link OnListItemClickListener}
     */

    @Override
    public void onListItemClick(@NonNull Suggestion object) {
        // TODO
    }


    /**
     * Private Methods
     */

    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mEmptyListLayout = rootView.findViewById(R.id.emptyListLayout);
        mDiseaseTextView = (TextView) rootView.findViewById(R.id.diseaseTextView);
        mGroupRiskTextView = (TextView) rootView.findViewById(R.id.groupRiskTextView);
        mSuggestionsTextView = (TextView) rootView.findViewById(R.id.suggestionsTextView);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.suggestionsList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * @return -1 if fail
     */
    private long getForecastId() {
        Intent intent = getActivity().getIntent();

        if (intent != null
                && intent.getExtras() != null
                && intent.getExtras().containsKey(ForecastItemActivity.KEY_FORECAST_ID)) {
            return intent.getExtras().getLong(ForecastItemActivity.KEY_FORECAST_ID);
        }
        return -1;
    }
}
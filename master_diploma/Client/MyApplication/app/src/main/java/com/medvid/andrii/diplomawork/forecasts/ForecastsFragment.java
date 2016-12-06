package com.medvid.andrii.diplomawork.forecasts;

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
import com.medvid.andrii.diplomawork.data.forecast.Forecast;
import com.medvid.andrii.diplomawork.forecasts.item.ForecastItemActivity;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.List;

public class ForecastsFragment extends Fragment
        implements ForecastsContract.View, OnListItemClickListener<Forecast> {

    private View mEmptyListLayout;
    private RecyclerView mRecyclerView;
    private ForecastsContract.Presenter mPresenter;
    private ForecastsListAdapter mForecastsListAdapter;

    public static ForecastsFragment newInstance() {
        return new ForecastsFragment();
    }

    public ForecastsFragment() {
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
        return inflater.inflate(R.layout.fragment_forecasts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

        if (mPresenter.fetchingDataFromServerTimeHasAlreadyCame()) {
            mPresenter.fetchForecastsApiCall();
        } else {
            mPresenter.getForecastsFromLocalStorage();
        }
    }

    /**
     * {@link ForecastsContract.View} methods
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(ForecastsContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void showListEmptyView(boolean show)    {
        mEmptyListLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void refreshList(@NonNull List<Forecast> forecastList) {
        Preconditions.checkNotNull(forecastList);

        if (mForecastsListAdapter == null) {
            mForecastsListAdapter = new ForecastsListAdapter(forecastList, ForecastsFragment.this);
            mRecyclerView.setAdapter(mForecastsListAdapter);
        } else {
            mForecastsListAdapter.refresh(forecastList);
        }
    }

    @Override
    public void showForecastItemScreen(long forecastId) {
        getActivity().startActivity(ForecastItemActivity.getIntent(getActivity(), forecastId));
    }

    /**
     * {@link OnListItemClickListener}
     */

    @Override
    public void onListItemClick(@NonNull Forecast object) {
        long forecastId = object.getId();
        showForecastItemScreen(forecastId);
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
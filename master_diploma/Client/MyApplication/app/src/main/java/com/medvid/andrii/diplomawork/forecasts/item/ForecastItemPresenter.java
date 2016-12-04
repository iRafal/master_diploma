package com.medvid.andrii.diplomawork.forecasts.item;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.data.forecast.Forecast;
import com.medvid.andrii.diplomawork.data.forecast.source.ForecastDataSourceContract;
import com.medvid.andrii.diplomawork.data.forecast.source.ForecastLocalDataSource;
import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;
import com.medvid.andrii.diplomawork.data.suggestion.source.SuggestionDataSourceContract;
import com.medvid.andrii.diplomawork.data.suggestion.source.SuggestionLocalDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ForecastItemPresenter implements ForecastItemContract.Presenter    {

    private final ForecastItemContract.View mView;

    public ForecastItemPresenter(@NonNull ForecastItemContract.View view) {
        mView = checkNotNull(view, ForecastItemContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void setSuggestionsDataOnUi(long forecastId) {

        SuggestionLocalDataSource suggestionLocalDataSource
                = SuggestionLocalDataSource.getInstance(DiplomaApplication.getInstance().getContentResolver());

        suggestionLocalDataSource.getSuggestions(forecastId, new SuggestionDataSourceContract.GetSuggestionsCallback() {
            @Override
            public void onSuggestionsLoaded(@NonNull List<Suggestion> suggestions) {
                if(suggestions.isEmpty())   {
                    mView.showListEmptyView(true);
                    return;
                }
                mView.refreshList(suggestions);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showListEmptyView(true);
            }
        });
    }

    @Override
    public void setForecastDataOnUi(long forecastId) {

        ForecastLocalDataSource forecastLocalDataSource
                = ForecastLocalDataSource.getInstance(
                DiplomaApplication.getInstance().getContentResolver());

        forecastLocalDataSource.getForecastSample(
                forecastId, new ForecastDataSourceContract.GetForecastSampleCallback() {

            @Override
            public void onForecastSampleLoaded(@NonNull Forecast forecast) {
                if(forecast == null)    {
                    // TODO
                    return;
                }

                mView.setDiseaseName(forecast.getDisease().getDiseaseName());
                mView.setGroupRiskName(forecast.getGroupRisk().getRiskName());
            }

            @Override
            public void onDataNotAvailable() {
                // TODO
            }
        });
    }

}

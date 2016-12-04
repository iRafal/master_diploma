package com.medvid.andrii.diplomawork.data.forecast;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class ForecastConverter {

    public static List<Forecast> getForecastsFromJsonResponseModelObject(
            @NonNull List<com.medvid.andrii.diplomawork.network.Forecast> forecastList)    {
            Preconditions.checkNotNull(forecastList);

        List<Forecast> list = new ArrayList<>(forecastList.size());

        long forecastId = 0;
        for(com.medvid.andrii.diplomawork.network.Forecast item : forecastList) {

            Disease disease = null;
            GroupRisk groupRisk = null;
            List<Suggestion> forecastSuggestionList = null;

            if(item.disease != null)    {
                disease = new Disease(item.disease.status, item.disease.name);
            }

            if(item.groupRisk != null)    {
                groupRisk = new GroupRisk(item.groupRisk.groupRiskType, item.groupRisk.Name);
            }

            if(item.suggestions != null && !item.suggestions.isEmpty())    {
                forecastSuggestionList = new ArrayList<>(item.suggestions.size());
                int i = 0;
                for(com.medvid.andrii.diplomawork.network.Forecast.Suggestion suggestionItem : item.suggestions) {
                        forecastSuggestionList.add(
                                new Suggestion(
                                        ++i,
                                        suggestionItem.description,
                                        suggestionItem.parameter == null ? null : suggestionItem.parameter.description,
                                        id));
                }
            }

            Forecast forecast = new Forecast(++forecastId, disease, groupRisk, forecastSuggestionList);
            list.add(forecast);
        }

        return list;
    }
}

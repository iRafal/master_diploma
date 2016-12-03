package com.medvid.andrii.diplomawork.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastsResponseObject {

    @SerializedName("Forecasts")
    public List<Forecast> forecastList;
}

package com.medvid.andrii.diplomawork.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("Disease")
    public Disease disease;

    @SerializedName("GroupRisk")
    public GroupRisk groupRisk;

    @SerializedName("Suggestions")
    public List<Suggestion> suggestions;

    private static class Disease   {
        @SerializedName("Status")
        int Status;

        @SerializedName("Name")
        String Name;
    }

    private static class GroupRisk   {

        @SerializedName("GroupRiskType")
        int GroupRiskType;

        @SerializedName("Name")
        String Name;
    }

    private static class Suggestion    {

        @SerializedName("Description")
        String Description;

        @SerializedName("Parameter")
        Parameter Parameter;
    }

    private static class Parameter   {
        @SerializedName("Description")
        String Description;
    }
}

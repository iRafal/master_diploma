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

    public static class Disease   {
        @SerializedName("Status")
        public int status;

        @SerializedName("Name")
        public String name;
    }

    public static class GroupRisk   {

        @SerializedName("GroupRiskType")
        public int groupRiskType;

        @SerializedName("Name")
        public String Name;
    }

    public static class Suggestion    {

        @SerializedName("Description")
        public String description;

        @SerializedName("Parameter")
        public Parameter parameter;
    }

    public static class Parameter   {
        @SerializedName("Description")
        public String description;
    }
}

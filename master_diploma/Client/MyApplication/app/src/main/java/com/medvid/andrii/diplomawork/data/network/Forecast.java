package com.medvid.andrii.diplomawork.data.network;

import java.util.List;

public class Forecast {

    public Disease Disease;
    public GroupRisk GroupRisk;
    public List<Suggestion> Suggestions;

    private static class Disease   {
        int Status;
        String Name;
    }

    private static class GroupRisk   {
        int GroupRiskType;
        String Name;
    }

    private static class Suggestion    {
        String Description;
        Parameter Parameter;
    }

    private static class Parameter   {
        String Description;
    }
}

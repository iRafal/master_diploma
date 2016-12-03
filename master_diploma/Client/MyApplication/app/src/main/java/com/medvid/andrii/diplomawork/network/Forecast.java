package com.medvid.andrii.diplomawork.network;

import java.util.List;

public class Forecast {

    public Disease disease;
    public GroupRisk groupRisk;
    public List<Suggestion> suggestions;

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

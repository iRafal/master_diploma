package com.medvid.andrii.diplomawork.data.forecast;

import com.medvid.andrii.diplomawork.data.ModelEntity;
import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;

import java.util.List;

public class Forecast implements ModelEntity    {

    private long id;
    private Disease disease;
    private GroupRisk groupRisk;
    private List<Suggestion> suggestionList;

    public Forecast(long id, Disease disease, GroupRisk groupRisk, List<Suggestion> suggestionList) {
        this.id = id;
        this.disease = disease;
        this.groupRisk = groupRisk;
        this.suggestionList = suggestionList;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", disease=" + disease +
                ", groupRisk=" + groupRisk +
                ", suggestionList=" + suggestionList +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public GroupRisk getGroupRisk() {
        return groupRisk;
    }

    public void setGroupRisk(GroupRisk groupRisk) {
        this.groupRisk = groupRisk;
    }

    public List<Suggestion> getSuggestionList() {
        return suggestionList;
    }

    public void setSuggestionList(List<Suggestion> suggestionList) {
        this.suggestionList = suggestionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecast forecast = (Forecast) o;

        if (id != forecast.id) return false;
        if (disease != null ? !disease.equals(forecast.disease) : forecast.disease != null)
            return false;
        if (groupRisk != null ? !groupRisk.equals(forecast.groupRisk) : forecast.groupRisk != null)
            return false;
        return suggestionList != null ? suggestionList.equals(forecast.suggestionList) : forecast.suggestionList == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (disease != null ? disease.hashCode() : 0);
        result = 31 * result + (groupRisk != null ? groupRisk.hashCode() : 0);
        result = 31 * result + (suggestionList != null ? suggestionList.hashCode() : 0);
        return result;
    }
}

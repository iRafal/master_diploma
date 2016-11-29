package com.medvid.andrii.diplomawork.data.suggestion;

import com.medvid.andrii.diplomawork.data.ModelEntity;

public class Suggestion implements ModelEntity {

    private long id;
    private String suggestionDescription;
    private String parameterDescription;
    private long forecaseId;

    public Suggestion(long id, String suggestionDescription, String parameterDescription, long forecaseId) {
        this.id = id;
        this.suggestionDescription = suggestionDescription;
        this.parameterDescription = parameterDescription;
        this.forecaseId = forecaseId;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id=" + id +
                ", suggestionDescription='" + suggestionDescription + '\'' +
                ", parameterDescription='" + parameterDescription + '\'' +
                ", forecaseId=" + forecaseId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSuggestionDescription() {
        return suggestionDescription;
    }

    public void setSuggestionDescription(String suggestionDescription) {
        this.suggestionDescription = suggestionDescription;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public long getForecaseId() {
        return forecaseId;
    }

    public void setForecaseId(long forecaseId) {
        this.forecaseId = forecaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suggestion that = (Suggestion) o;

        if (id != that.id) return false;
        if (forecaseId != that.forecaseId) return false;
        if (suggestionDescription != null ? !suggestionDescription.equals(that.suggestionDescription) : that.suggestionDescription != null)
            return false;
        return parameterDescription != null ? parameterDescription.equals(that.parameterDescription) : that.parameterDescription == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (suggestionDescription != null ? suggestionDescription.hashCode() : 0);
        result = 31 * result + (parameterDescription != null ? parameterDescription.hashCode() : 0);
        result = 31 * result + (int) (forecaseId ^ (forecaseId >>> 32));
        return result;
    }
}

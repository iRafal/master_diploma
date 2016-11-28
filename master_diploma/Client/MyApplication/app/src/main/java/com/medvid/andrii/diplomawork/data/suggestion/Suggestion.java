package com.medvid.andrii.diplomawork.data.suggestion;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.ModelEntity;
import com.medvid.andrii.diplomawork.data.disease.Disease;
import com.medvid.andrii.diplomawork.data.suggestion.parameter.Parameter;

public class Suggestion implements ModelEntity {

    private int id;
    private String description;
    private Disease mDisease;
    private Parameter mParameter;

    public Suggestion(int id, String description, @NonNull Disease disease,
                      @NonNull Parameter parameter) {

        Preconditions.checkNotNull(disease);
        Preconditions.checkNotNull(parameter);

        this.id = id;
        this.description = description;
        this.mDisease = disease;
        mParameter = parameter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Disease getDisease() {
        return mDisease;
    }

    public void setDisease(Disease disease) {
        mDisease = disease;
    }

    public Parameter getParameter() {
        return mParameter;
    }

    public void setParameter(Parameter parameter) {
        mParameter = parameter;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", mDisease=" + mDisease +
                ", mParameter=" + mParameter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suggestion that = (Suggestion) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (mDisease != null ? !mDisease.equals(that.mDisease) : that.mDisease != null)
            return false;
        return mParameter != null ? mParameter.equals(that.mParameter) : that.mParameter == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (mDisease != null ? mDisease.hashCode() : 0);
        result = 31 * result + (mParameter != null ? mParameter.hashCode() : 0);
        return result;
    }
}

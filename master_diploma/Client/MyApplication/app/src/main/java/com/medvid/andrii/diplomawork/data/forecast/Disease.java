package com.medvid.andrii.diplomawork.data.forecast;

import com.medvid.andrii.diplomawork.data.ModelEntity;

public class Disease implements ModelEntity {

    private long id;
    private String diseaseName;

    public Disease(long id, String disseaseName) {
        this.id = id;
        this.diseaseName = disseaseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDisseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", diseaseName=" + diseaseName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        if (id != disease.id) return false;
        return diseaseName != null ? diseaseName.equals(disease.diseaseName) : disease.diseaseName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (diseaseName != null ? diseaseName.hashCode() : 0);
        return result;
    }
}

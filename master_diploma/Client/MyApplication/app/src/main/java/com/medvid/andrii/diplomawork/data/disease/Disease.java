package com.medvid.andrii.diplomawork.data.disease;

import com.medvid.andrii.diplomawork.data.ModelEntity;

public class Disease implements ModelEntity {

    private int id;
    private String disseaseName;

    public Disease(int id, String disseaseName) {
        this.id = id;
        this.disseaseName = disseaseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisseaseName() {
        return disseaseName;
    }

    public void setDisseaseName(String disseaseName) {
        this.disseaseName = disseaseName;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", disseaseName=" + disseaseName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        if (id != disease.id) return false;
        return disseaseName != null ? disseaseName.equals(disease.disseaseName) : disease.disseaseName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (disseaseName != null ? disseaseName.hashCode() : 0);
        return result;
    }
}

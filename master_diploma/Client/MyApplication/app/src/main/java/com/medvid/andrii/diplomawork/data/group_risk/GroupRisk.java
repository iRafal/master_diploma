package com.medvid.andrii.diplomawork.data.group_risk;

import com.medvid.andrii.diplomawork.data.ModelEntity;

public class GroupRisk implements ModelEntity {

    private int id;
    private String riskName;

    public GroupRisk(int id, String riskName) {
        this.id = id;
        this.riskName = riskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    @Override
    public String toString() {
        return "GroupRisk{" +
                "id=" + id +
                ", riskName='" + riskName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupRisk groupRisk = (GroupRisk) o;

        if (id != groupRisk.id) return false;
        return riskName != null ? riskName.equals(groupRisk.riskName) : groupRisk.riskName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (riskName != null ? riskName.hashCode() : 0);
        return result;
    }
}

package com.medvid.andrii.diplomawork.data.training_sample.entities;

public class Calories {

    double spentCalories; // 2500
    double eatenCalories; //3000

    public Calories(double spentCalories, double eatenCalories) {
        this.spentCalories = spentCalories;
        this.eatenCalories = eatenCalories;
    }

    public double getSpentCalories() {
        return spentCalories;
    }

    public void setSpentCalories(double spentCalories) {
        this.spentCalories = spentCalories;
    }

    public double getEatenCalories() {
        return eatenCalories;
    }

    public void setEatenCalories(double eatenCalories) {
        this.eatenCalories = eatenCalories;
    }

    @Override
    public String toString() {
        return "Calories{" +
                "spentCalories=" + spentCalories +
                ", eatenCalories=" + eatenCalories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calories calories = (Calories) o;

        if (Double.compare(calories.spentCalories, spentCalories) != 0) return false;
        return Double.compare(calories.eatenCalories, eatenCalories) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(spentCalories);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(eatenCalories);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

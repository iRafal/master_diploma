package com.medvid.andrii.diplomawork.data.info;

import com.medvid.andrii.diplomawork.data.ModelEntity;

public class Info implements ModelEntity {

    private long id;
    private double distance; //  4
    private Sleep mSleep;
    private Calories mCalories;
    private double foodMultiplicity; //3
    private double fatAmount; // 75
    private double carbohydrateAmount; // 200
    private double proteinAmount; //  85
    private double vitaminC; //  60
    private double sugarLevel; //  3
    private double stressLevel; //  3
    private double temperature; //  36.6
    private Pressure mPressure;
    private double pulse; // 60
    private long timeStamp;

    public Info(long id, double distance, Sleep sleep, Calories calories, double foodMultiplicity,
                double fatAmount, double carbohydrateAmount, double proteinAmount,
                double vitaminC, double sugarLevel, double stressLevel, double temperature,
                Pressure pressure, double pulse) {

        this.id = id;
        this.distance = distance;
        this. mSleep = sleep;
        this. mCalories = calories;
        this.foodMultiplicity = foodMultiplicity;
        this.fatAmount = fatAmount;
        this.carbohydrateAmount = carbohydrateAmount;
        this.proteinAmount = proteinAmount;
        this.vitaminC = vitaminC;
        this.sugarLevel = sugarLevel;
        this.stressLevel = stressLevel;
        this.temperature = temperature;
        this.mPressure = pressure;
        this.pulse = pulse;
        setTimeStamp();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Sleep getSleep() {
        return mSleep;
    }

    public void setSleep(Sleep sleep) {
        mSleep = sleep;
    }

    public Calories getCalories() {
        return mCalories;
    }

    public void setCalories(Calories calories) {
        mCalories = calories;
    }

    public double getFoodMultiplicity() {
        return foodMultiplicity;
    }

    public void setFoodMultiplicity(double foodMultiplicity) {
        this.foodMultiplicity = foodMultiplicity;
    }

    public double getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(double fatAmount) {
        this.fatAmount = fatAmount;
    }

    public double getCarbohydrateAmount() {
        return carbohydrateAmount;
    }

    public void setCarbohydrateAmount(double carbohydrateAmount) {
        this.carbohydrateAmount = carbohydrateAmount;
    }

    public double getProteinAmount() {
        return proteinAmount;
    }

    public void setProteinAmount(double proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public double getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(double sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public double getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(double stressLevel) {
        this.stressLevel = stressLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Pressure getPressure() {
        return mPressure;
    }

    public void setPressure(Pressure pressure) {
        mPressure = pressure;
    }

    public double getPulse() {
        return pulse;
    }

    public void setPulse(double pulse) {
        this.pulse = pulse;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Calculates timestamp
     */
    public void setTimeStamp() {
        this.timeStamp = System.currentTimeMillis() / 1000;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", distance=" + distance +
                ", mSleep=" + mSleep +
                ", mCalories=" + mCalories +
                ", foodMultiplicity=" + foodMultiplicity +
                ", fatAmount=" + fatAmount +
                ", carbohydrateAmount=" + carbohydrateAmount +
                ", proteinAmount=" + proteinAmount +
                ", vitaminC=" + vitaminC +
                ", sugarLevel=" + sugarLevel +
                ", stressLevel=" + stressLevel +
                ", temperature=" + temperature +
                ", mPressure=" + mPressure +
                ", pulse=" + pulse +
                ", timeStamp=" + timeStamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (id != info.id) return false;
        if (Double.compare(info.distance, distance) != 0) return false;
        if (Double.compare(info.foodMultiplicity, foodMultiplicity) != 0) return false;
        if (Double.compare(info.fatAmount, fatAmount) != 0) return false;
        if (Double.compare(info.carbohydrateAmount, carbohydrateAmount) != 0) return false;
        if (Double.compare(info.proteinAmount, proteinAmount) != 0) return false;
        if (Double.compare(info.vitaminC, vitaminC) != 0) return false;
        if (Double.compare(info.sugarLevel, sugarLevel) != 0) return false;
        if (Double.compare(info.stressLevel, stressLevel) != 0) return false;
        if (Double.compare(info.temperature, temperature) != 0) return false;
        if (Double.compare(info.pulse, pulse) != 0) return false;
        if (timeStamp != info.timeStamp) return false;
        if (mSleep != null ? !mSleep.equals(info.mSleep) : info.mSleep != null) return false;
        if (mCalories != null ? !mCalories.equals(info.mCalories) : info.mCalories != null)
            return false;
        return mPressure != null ? mPressure.equals(info.mPressure) : info.mPressure == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mSleep != null ? mSleep.hashCode() : 0);
        result = 31 * result + (mCalories != null ? mCalories.hashCode() : 0);
        temp = Double.doubleToLongBits(foodMultiplicity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fatAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(carbohydrateAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(proteinAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vitaminC);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sugarLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stressLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (mPressure != null ? mPressure.hashCode() : 0);
        temp = Double.doubleToLongBits(pulse);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (timeStamp ^ (timeStamp >>> 32));
        return result;
    }
}

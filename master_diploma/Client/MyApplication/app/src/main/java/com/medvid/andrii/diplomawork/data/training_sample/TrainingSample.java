package com.medvid.andrii.diplomawork.data.training_sample;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.ModelEntity;
import com.medvid.andrii.diplomawork.data.user.User;

public class TrainingSample implements ModelEntity {

    private long id;
    private double age;
    private @User.Gender int gender; // 1
    private double growth; // 1.8
    private double weight; // 75
    private double bodyMassIndex; // 20
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
    private boolean isForecast;
    private boolean isStatistics;

    public TrainingSample() {
        // Test empty constructor
    }

    public TrainingSample(long id, double age, @User.Gender int gender, double growth, double weight,
                          double bodyMassIndex, double distance, Sleep sleep, Calories calories,
                          double foodMultiplicity, double fatAmount, double carbohydrateAmount,
                          double proteinAmount, double vitaminC, double sugarLevel, double stressLevel,
                          double temperature, Pressure pressure, double pulse, long timestamp,
                          boolean isForecast, boolean isStatistics) {

        Preconditions.checkNotNull(sleep);
        Preconditions.checkNotNull(calories);
        Preconditions.checkNotNull(pressure);

        this.id = id;
        this.age = age;
        this.gender = gender;
        this.growth = growth;
        this.weight = weight;
        this.bodyMassIndex = bodyMassIndex;
        this.distance = distance;
        mSleep = sleep;
        mCalories = calories;
        this.foodMultiplicity = foodMultiplicity;
        this.fatAmount = fatAmount;
        this.carbohydrateAmount = carbohydrateAmount;
        this.proteinAmount = proteinAmount;
        this.vitaminC = vitaminC;
        this.sugarLevel = sugarLevel;
        this.stressLevel = stressLevel;
        this.temperature = temperature;
        mPressure = pressure;
        this.pulse = pulse;
        this.timeStamp = timestamp;
        this.isForecast = isForecast;
        this.isStatistics = isStatistics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public @User.Gender int getGender() {
        return gender;
    }

    public void setGender(@User.Gender int gender) {
        this.gender = gender;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
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

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isForecast() {
        return isForecast;
    }

    public void setForecast(boolean isForecast) {
        isForecast = isForecast;
    }

    public boolean isStatistics() {
        return isStatistics;
    }

    public void setStatistics(boolean statistics) {
        isStatistics = statistics;
    }

    @Override
    public String toString() {
        return "TrainingSample{" +
                "id=" + id +
                ", age=" + age +
                ", gender=" + gender +
                ", growth=" + growth +
                ", weight=" + weight +
                ", bodyMassIndex=" + bodyMassIndex +
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
                ", isForecast=" + isForecast +
                ", isStatistics=" + isStatistics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingSample that = (TrainingSample) o;

        if (id != that.id) return false;
        if (Double.compare(that.age, age) != 0) return false;
        if (gender != that.gender) return false;
        if (Double.compare(that.growth, growth) != 0) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (Double.compare(that.bodyMassIndex, bodyMassIndex) != 0) return false;
        if (Double.compare(that.distance, distance) != 0) return false;
        if (Double.compare(that.foodMultiplicity, foodMultiplicity) != 0) return false;
        if (Double.compare(that.fatAmount, fatAmount) != 0) return false;
        if (Double.compare(that.carbohydrateAmount, carbohydrateAmount) != 0) return false;
        if (Double.compare(that.proteinAmount, proteinAmount) != 0) return false;
        if (Double.compare(that.vitaminC, vitaminC) != 0) return false;
        if (Double.compare(that.sugarLevel, sugarLevel) != 0) return false;
        if (Double.compare(that.stressLevel, stressLevel) != 0) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (Double.compare(that.pulse, pulse) != 0) return false;
        if (timeStamp != that.timeStamp) return false;
        if (isForecast != that.isForecast) return false;
        if (isStatistics != that.isStatistics) return false;
        if (mSleep != null ? !mSleep.equals(that.mSleep) : that.mSleep != null) return false;
        if (mCalories != null ? !mCalories.equals(that.mCalories) : that.mCalories != null)
            return false;
        return mPressure != null ? mPressure.equals(that.mPressure) : that.mPressure == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(age);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + gender;
        temp = Double.doubleToLongBits(growth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bodyMassIndex);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
        result = 31 * result + (isForecast ? 1 : 0);
        result = 31 * result + (isStatistics ? 1 : 0);
        return result;
    }
}

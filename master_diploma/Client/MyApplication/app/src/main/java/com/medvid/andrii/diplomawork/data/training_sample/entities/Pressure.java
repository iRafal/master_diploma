package com.medvid.andrii.diplomawork.data.training_sample.entities;

public class Pressure {

    private double highPressure; //  120
    private double lowPressure; //  90

    public Pressure(double highPressure, double lowPressure) {
        this.highPressure = highPressure;
        this.lowPressure = lowPressure;
    }

    public double getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(double highPressure) {
        this.highPressure = highPressure;
    }

    public double getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(double lowPressure) {
        this.lowPressure = lowPressure;
    }

    @Override
    public String toString() {
        return "Pressure{" +
                "highPressure=" + highPressure +
                ", lowPressure=" + lowPressure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pressure pressure = (Pressure) o;

        if (Double.compare(pressure.highPressure, highPressure) != 0) return false;
        return Double.compare(pressure.lowPressure, lowPressure) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(highPressure);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lowPressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

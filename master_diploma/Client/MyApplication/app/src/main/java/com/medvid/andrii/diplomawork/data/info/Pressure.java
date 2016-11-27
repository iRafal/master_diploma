package com.medvid.andrii.diplomawork.data.info;

public class Pressure {

    private double hightPressure; //  120
    private double lowPressure; //  90

    public Pressure(double hightPressure, double lowPressure) {
        this.hightPressure = hightPressure;
        this.lowPressure = lowPressure;
    }

    public double getHightPressure() {
        return hightPressure;
    }

    public void setHightPressure(double hightPressure) {
        this.hightPressure = hightPressure;
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
                "hightPressure=" + hightPressure +
                ", lowPressure=" + lowPressure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pressure pressure = (Pressure) o;

        if (Double.compare(pressure.hightPressure, hightPressure) != 0) return false;
        return Double.compare(pressure.lowPressure, lowPressure) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(hightPressure);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lowPressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

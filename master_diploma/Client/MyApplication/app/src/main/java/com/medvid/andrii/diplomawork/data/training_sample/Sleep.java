package com.medvid.andrii.diplomawork.data.training_sample;

public class Sleep {

    private double sleepHoursCount; //8
    private double sleepQuality; //8   Value: [1.0...10.0]

    public Sleep(double sleepHoursCount, double sleepQuality) {
        this.sleepHoursCount = sleepHoursCount;
        this.sleepQuality = sleepQuality;
    }

    public double getSleepHoursCount() {
        return sleepHoursCount;
    }

    public void setSleepHoursCount(double sleepHoursCount) {
        this.sleepHoursCount = sleepHoursCount;
    }

    public double getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(double sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    @Override
    public String toString() {
        return "Sleep{" +
                "sleepHoursCount=" + sleepHoursCount +
                ", sleepQuality=" + sleepQuality +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sleep sleep = (Sleep) o;

        if (Double.compare(sleep.sleepHoursCount, sleepHoursCount) != 0) return false;
        return Double.compare(sleep.sleepQuality, sleepQuality) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(sleepHoursCount);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sleepQuality);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}


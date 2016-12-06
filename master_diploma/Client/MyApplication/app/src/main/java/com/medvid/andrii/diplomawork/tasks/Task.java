package com.medvid.andrii.diplomawork.tasks;

import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;

public class Task {

    private boolean isPassed;
    private TrainingSample trainingSample;

    public Task(boolean isPassed, TrainingSample trainingSample) {
        this.isPassed = isPassed;
        this.trainingSample = trainingSample;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public TrainingSample getTrainingSample() {
        return trainingSample;
    }

    public void setTrainingSample(TrainingSample trainingSample) {
        this.trainingSample = trainingSample;
    }

    @Override
    public String toString() {
        return "Task{" +
                "isPassed=" + isPassed +
                ", trainingSample=" + trainingSample +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (isPassed != task.isPassed) return false;
        return trainingSample != null ? trainingSample.equals(task.trainingSample) : task.trainingSample == null;

    }

    @Override
    public int hashCode() {
        int result = (isPassed ? 1 : 0);
        result = 31 * result + (trainingSample != null ? trainingSample.hashCode() : 0);
        return result;
    }
}

package com.medvid.andrii.diplomawork.tasks;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Calories;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Pressure;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Sleep;
import com.medvid.andrii.diplomawork.data.training_sample.source.TrainingSamplesLocalDataSource;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.AccountManager;
import com.medvid.andrii.diplomawork.util.RandomUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksPresenter implements TasksContract.Presenter {

    private final TasksContract.View mView;

    public TasksPresenter(@NonNull TasksContract.View view) {
        mView = checkNotNull(view, TasksContract.View.class.getSimpleName() + " cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void setTrainingSamplesFromLocalStorage() {

        saveFakeTrainingSamplesToDb();

        TrainingSamplesLocalDataSource source
                = TrainingSamplesLocalDataSource.getInstance(DiplomaApplication.getInstance().getContentResolver());

        source.getTrainingSamples(
                new TrainingSamplesLocalDataSource.GetTrainingSamplesCallback() {

                    @Override
                    public void onTrainingSamplesLoaded(@NonNull List<TrainingSample> trainingSamples) {
                        if (trainingSamples.isEmpty()) {
                            mView.showListEmptyView(true);
                            return;
                        }

                        mView.showListEmptyView(false);

                        mView.refreshList(convertList(trainingSamples));
                    }

                    @Override
                    public void onDataNotAvailable() {
                        mView.showListEmptyView(true);
                    }
                });
    }

    private List<Task> convertList(@NonNull List<TrainingSample> trainingSamples)   {
        checkNotNull(trainingSamples);

        List<Task> list = new ArrayList<>(trainingSamples.size());

        if(trainingSamples.get(0).getTimeStamp().getTime() < new Date().getTime())    {
            list.add(new Task(false, null));
        }

        for(TrainingSample trainingSample : trainingSamples)    {
            list.add(new Task(true, trainingSample));
        }

        return list;
    }

    public TrainingSample getFakeTrainingSample(long id) {

        AccountManager accountManager = new AccountManager(DiplomaApplication.getInstance());
        User user = accountManager.getUserData();

        if(user == null)    {
            return null;
        }

        double age = user.getAge(); // Auto
        double growth = user.getGrowth();   // Auto
        double weight = user.getWeight();   // Auto
        double bodyMassIndex = user.getBodyMassIndex(); // Auto

        RandomUtils randomUtils = new RandomUtils();
        double distance = randomUtils.nextDouble(1.0, 4.0);  //User input ok
        Sleep mSleep = new Sleep(
                randomUtils.nextDouble(6.0, 10.0),// SleepHoursCount   //User input ok
                randomUtils.nextDouble(0.0, 10.0)// SleepQuality // Separate screen.  //User input ok
        );

        Calories mCalories = new Calories(
                randomUtils.nextDouble(2000.0, 5000.0),   // SpentCalories
                randomUtils.nextDouble(2000.0, 5000.0)    // EatenCalories
        );
        final double foodMultiplicity = randomUtils.nextDouble(3.0, 4.0);   //User input ok
        double fatAmount = randomUtils.nextDouble(-10.0, 10.0);
        double carbohydrateAmount = weight * 4 + randomUtils.nextDouble(-15.0, 15.0);
        double proteinAmount = weight + randomUtils.nextDouble(-10.0, 10.0);
        double vitaminC = randomUtils.nextDouble(55.0, 65.0);
        double sugarLevel = randomUtils.nextDouble(3.0, 5.0);  // Stub
        double stressLevel = randomUtils.nextDouble(3.0, 5.0);  // Separate screen.  //User input ok
        double temperature = 36.6;  //User input ok
        Pressure mPressure = new Pressure(
                randomUtils.nextDouble(115.0, 130.0),     //User input ok
                randomUtils.nextDouble(75.0, 85.0)    //User input ok
        );

        double pulse = randomUtils.nextDouble(60.0, 80.0);    //User input ok
        Date timeStamp = new Date(new Date().getTime() - id * 24 * 60 * 60 * 1000); // Auto

        TrainingSample trainingSample =
                TrainingSample.getSampleTrainingSample(
                        id,
                        age,
                        User.Gender.MAN,
                        growth,
                        weight,
                        bodyMassIndex,
                        distance,
                        mSleep,
                        mCalories,
                        foodMultiplicity,
                        fatAmount,
                        carbohydrateAmount,
                        proteinAmount,
                        vitaminC,
                        sugarLevel,
                        stressLevel,
                        temperature,
                        mPressure,
                        pulse,
                        timeStamp
                );

        return trainingSample;
    }

    private void saveFakeTrainingSamplesToDb()  {
        TrainingSamplesLocalDataSource source
                = TrainingSamplesLocalDataSource.getInstance(
                DiplomaApplication.getInstance().getContentResolver());

        List<TrainingSample> list = new ArrayList<>();

        for(int i = 0; i < 5; ++i)  {
            list.add(getFakeTrainingSample(i+1));
        }

        long count = source.saveTrainingSamples(list);
        count++;
    }
}

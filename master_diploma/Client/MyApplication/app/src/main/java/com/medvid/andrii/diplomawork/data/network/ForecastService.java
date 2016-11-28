package com.medvid.andrii.diplomawork.data.network;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastService {

    private static String AGE = "Age";
    private static String GENDER = "Gender";
    private static String GROWTH = "Growth";
    private static String WEIGHT = "Weight";
    private static String BODY_MASS_INDEX = "BodyMassIndex";
    private static String DISTANCE = "Distance";
    private static String SLEEP_HOURS_COUNT = "SleepHoursCount";
    private static String SLEEP_QUALITY = "SleepQuality";
    private static String SPENT_CALORIES = "SpentCalories";
    private static String EATEN_CALORIES = "EatenCalories";
    private static String FOOD_MULTIPLICITY = "FoodMultiplicity";
    private static String FAT_AMOUNT = "FatAmount";
    private static String CARBOHYDRATE_AMOUNT = "CarbohydrateAmount";
    private static String PROTEIN_AMOUNT = "ProteinAmount";
    private static String VITAMIN_C = "VitaminC";
    private static String SUGAR_LEVEL = "SugarLevel";
    private static String STRESS_LEVEL = "StressLevel";
    private static String TEMPERATURE = "Temperature";
    private static String HIGHT_PRESSURE = "HightPressure";
    private static String LOW_PRESSURE = "LowPressure";
    private static String PULSE = "Pulse";

    private static final String DOMAIN = "http://localhost:56906/";

    public static Map<String, String> getQueryMap(@NonNull TrainingSample trainingSample) {
        Preconditions.checkNotNull(trainingSample);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put(AGE, Double.toString(trainingSample.getAge()));
        paramsMap.put(GENDER, Integer.toString(trainingSample.getGender()));
        paramsMap.put(GROWTH, Double.toString(trainingSample.getGrowth()));
        paramsMap.put(WEIGHT, Double.toString(trainingSample.getWeight()));
        paramsMap.put(BODY_MASS_INDEX, Double.toString(trainingSample.getBodyMassIndex()));
        paramsMap.put(DISTANCE, Double.toString(trainingSample.getDistance()));
        paramsMap.put(SLEEP_HOURS_COUNT, Double.toString(trainingSample.getSleep().getSleepHoursCount()));
        paramsMap.put(SLEEP_QUALITY, Double.toString(trainingSample.getSleep().getSleepQuality()));
        paramsMap.put(SPENT_CALORIES, Double.toString(trainingSample.getCalories().getSpentCalories()));
        paramsMap.put(EATEN_CALORIES, Double.toString(trainingSample.getCalories().getEatenCalories()));
        paramsMap.put(FOOD_MULTIPLICITY, Double.toString(trainingSample.getFoodMultiplicity()));
        paramsMap.put(FAT_AMOUNT, Double.toString(trainingSample.getFatAmount()));
        paramsMap.put(CARBOHYDRATE_AMOUNT, Double.toString(trainingSample.getCarbohydrateAmount()));
        paramsMap.put(PROTEIN_AMOUNT, Double.toString(trainingSample.getProteinAmount()));
        paramsMap.put(VITAMIN_C, Double.toString(trainingSample.getVitaminC()));
        paramsMap.put(SUGAR_LEVEL, Double.toString(trainingSample.getSugarLevel()));
        paramsMap.put(STRESS_LEVEL, Double.toString(trainingSample.getStressLevel()));
        paramsMap.put(TEMPERATURE, Double.toString(trainingSample.getTemperature()));
        paramsMap.put(HIGHT_PRESSURE, Double.toString(trainingSample.getPressure().getHighPressure()));
        paramsMap.put(LOW_PRESSURE, Double.toString(trainingSample.getPressure().getLowPressure()));
        paramsMap.put(PULSE, Double.toString(trainingSample.getPulse()));

        return paramsMap;
    }

    public static void getForecast(@NonNull TrainingSample trainingSample,
                                   final Callback<List<Forecast>> callback)    {

        Preconditions.checkNotNull(trainingSample);
        Preconditions.checkNotNull(callback);

        OkHttpClient okClient = new OkHttpClient.Builder().build();
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOMAIN)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ForecastServiceProtocol forecastServiceProtocol = retrofit.create(ForecastServiceProtocol.class);
        Call<List<Forecast>> call = forecastServiceProtocol.getForecast(getQueryMap(new TrainingSample()));
        call.enqueue(new Callback<List<Forecast>>() {
            @Override
            public void onResponse(Call<List<Forecast>> call, Response<List<Forecast>> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<List<Forecast>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}

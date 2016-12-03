package com.medvid.andrii.diplomawork.network;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.AGE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.BODY_MASS_INDEX;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.CARBOHYDRATE_AMOUNT;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.DISTANCE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.EATEN_CALORIES;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.FAT_AMOUNT;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.FOOD_MULTIPLICITY;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.GENDER;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.GROWTH;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.HIGHT_PRESSURE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.LOW_PRESSURE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.PROTEIN_AMOUNT;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.PULSE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.SLEEP_HOURS_COUNT;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.SLEEP_QUALITY;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.SPENT_CALORIES;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.STRESS_LEVEL;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.SUGAR_LEVEL;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.TEMPERATURE;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.VITAMIN_C;
import static com.medvid.andrii.diplomawork.network.ForecastService.QueryParams.WEIGHT;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class ForecastService {

    @Retention(SOURCE)
    @StringDef({
            AGE, GENDER, GROWTH, WEIGHT, BODY_MASS_INDEX, DISTANCE, SLEEP_HOURS_COUNT,
            SLEEP_QUALITY, SPENT_CALORIES, EATEN_CALORIES, FOOD_MULTIPLICITY, FAT_AMOUNT,
            CARBOHYDRATE_AMOUNT, PROTEIN_AMOUNT, VITAMIN_C, SUGAR_LEVEL, STRESS_LEVEL,
            TEMPERATURE, HIGHT_PRESSURE, LOW_PRESSURE, PULSE
    })

    public @interface QueryParams {
        String AGE = "Age";
        String GENDER = "Gender";
        String GROWTH = "Growth";
        String WEIGHT = "Weight";
        String BODY_MASS_INDEX = "BodyMassIndex";
        String DISTANCE = "Distance";
        String SLEEP_HOURS_COUNT = "SleepHoursCount";
        String SLEEP_QUALITY = "SleepQuality";
        String SPENT_CALORIES = "SpentCalories";
        String EATEN_CALORIES = "EatenCalories";
        String FOOD_MULTIPLICITY = "FoodMultiplicity";
        String FAT_AMOUNT = "FatAmount";
        String CARBOHYDRATE_AMOUNT = "CarbohydrateAmount";
        String PROTEIN_AMOUNT = "ProteinAmount";
        String VITAMIN_C = "VitaminC";
        String SUGAR_LEVEL = "SugarLevel";
        String STRESS_LEVEL = "StressLevel";
        String TEMPERATURE = "Temperature";
        String HIGHT_PRESSURE = "HightPressure";
        String LOW_PRESSURE = "LowPressure";
        String PULSE = "Pulse";
    }

    private static final String DOMAIN = "http://10.0.2.2:56906/";

    private static Map<String, String> getQueryMap(@NonNull TrainingSample trainingSample) {
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
                                   final Callback<ForecastsResponseObject> callback)    {

        Preconditions.checkNotNull(trainingSample);
        Preconditions.checkNotNull(callback);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOMAIN)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForecastServiceProtocol forecastServiceProtocol = retrofit.create(ForecastServiceProtocol.class);
        Call<ForecastsResponseObject> call = forecastServiceProtocol.getForecast(getQueryMap(trainingSample));
        call.enqueue(new NetworkCallback<>(callback));
    }
}

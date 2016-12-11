package com.medvid.andrii.diplomawork.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface ForecastServiceProtocol {

    /**
     * http://localhost:56906/api/NeuralNetwork/GetForecast?Age=18&Gender=1&Growth=1.8&Weight=75&BodyMassIndex=20&Distance=4&SleepHoursCount=8&SleepQuality=8&SpentCalories=2500&EatenCalories=3000&FoodMultiplicity=3&FatAmount=75&CarbohydrateAmount=200&ProteinAmount=85&VitaminC=60&SugarLevel=3&StressLevel=3&Temperature=36.6&HightPressure=120&LowPressure=90&Pulse=60
     */
    @Headers("Content-Type: application/json")
    @GET("api/NeuralNetwork/GetForecast")
    Call<ForecastsResponseObject> getForecast(@QueryMap Map<String, String> queryParams);
}

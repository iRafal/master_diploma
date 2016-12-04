package com.medvid.andrii.diplomawork.forecasts;

import android.content.Context;
import android.content.SharedPreferences;

import com.medvid.andrii.diplomawork.DiplomaApplication;

public class ForecastsCookies {

    public static final String KEY_FORECAST_PREFERENCES
            = "com.medvid.andrii.diplomawork.forecasts.KEY_FORECAST_PREFERENCES";

    public static final String KEY_CURRENT_DATE
            = "com.medvid.andrii.diplomawork.forecasts.KEY_CURRENT_DATE";


    private static DiplomaApplication sDiplomaApplication
            = DiplomaApplication.getInstance();

    public static void saveCurrentTime(long currentDate)	{
        SharedPreferences prefs = sDiplomaApplication.getSharedPreferences(KEY_FORECAST_PREFERENCES, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(KEY_CURRENT_DATE, currentDate);
        editor.commit();
    }

    public static long getSavedTime()	{
        SharedPreferences prefs = sDiplomaApplication.getSharedPreferences(KEY_FORECAST_PREFERENCES, Context.MODE_PRIVATE );
        return prefs.getLong(KEY_CURRENT_DATE, 0);
    }

    public static void removeSavedTime()	{
        SharedPreferences prefs = sDiplomaApplication.getSharedPreferences(KEY_FORECAST_PREFERENCES, Context.MODE_PRIVATE );
        prefs.edit().remove(KEY_CURRENT_DATE).commit();
    }
}

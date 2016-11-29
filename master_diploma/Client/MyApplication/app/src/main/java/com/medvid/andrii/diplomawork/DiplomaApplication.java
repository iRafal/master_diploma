package com.medvid.andrii.diplomawork;

import android.app.Application;

public class DiplomaApplication extends Application {

    private static DiplomaApplication INSTANCE;

    public static DiplomaApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}

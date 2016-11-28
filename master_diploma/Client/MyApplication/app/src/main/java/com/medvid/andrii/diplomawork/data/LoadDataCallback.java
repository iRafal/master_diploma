package com.medvid.andrii.diplomawork.data;

import android.database.Cursor;

public interface LoadDataCallback {

    void onDataLoaded(Cursor data);

    void onDataEmpty();

    void onDataNotAvailable();

    void onDataReset();
}

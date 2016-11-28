package com.medvid.andrii.diplomawork.data.disease;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */

public interface DiseaseDataSourceContract {

    interface GetDiseasesCallback {

        void onDiseasesLoaded(@NonNull List<Disease> diseases);

        void onDataNotAvailable();
    }

    interface GetDiseaseCallback {

        void onDiseaseLoaded(@NonNull Disease disease);

        void onDataNotAvailable();
    }

    void saveDisease(@NonNull Disease parameter);

    void getDisease(@NonNull String id, @NonNull GetDiseaseCallback callback);

    void getDiseases(@NonNull GetDiseasesCallback callback);

    void deleteDisease(@NonNull String id);

    void deleteAllDiseases();

    void updateDisease(@NonNull Disease suggestion);

}

package com.medvid.andrii.diplomawork.data.statistics;

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

public interface StatisticsDataSourceContract {

    interface GetStatisticCallback {

        void onStatisticLoaded(@NonNull List<Statistics> statistics);

        void onDataNotAvailable();
    }

    interface GetStatisticsCallback {

        void onStatisticsLoaded(@NonNull Statistics statistics);

        void onDataNotAvailable();
    }

    void saveStatistics(@NonNull Statistics statistics);

    void getStatistics(@NonNull String id, @NonNull GetStatisticsCallback callback);

    void getStatisticss(@NonNull GetStatisticCallback callback);

    void deleteStatistics(@NonNull String id);

    void deleteAllStatisticss();

    void updateStatistics(@NonNull Statistics statistics);

}

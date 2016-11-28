package com.medvid.andrii.diplomawork.data.group_risk;

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

public interface GroupRiskDataSourceContract {

    interface GetGroupRisksCallback {

        void onGroupRisksLoaded(@NonNull List<GroupRisk> groupRisks);

        void onDataNotAvailable();
    }

    interface GetGroupRiskCallback {

        void onGroupRiskLoaded(@NonNull GroupRisk groupRisk);

        void onDataNotAvailable();
    }

    void saveGroupRisk(@NonNull GroupRisk groupRisk);

    void getGroupRisk(@NonNull String id, @NonNull GetGroupRiskCallback callback);

    void getGroupRisks(@NonNull GetGroupRisksCallback callback);

    void deleteGroupRisk(@NonNull String id);

    void deleteAllGroupRisks();

    void updateGroupRisk(@NonNull GroupRisk suggestion);

}

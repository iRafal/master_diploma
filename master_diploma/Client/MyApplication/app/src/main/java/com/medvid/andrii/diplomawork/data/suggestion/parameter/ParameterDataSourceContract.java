package com.medvid.andrii.diplomawork.data.suggestion.parameter;

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

public interface ParameterDataSourceContract {

    interface GetParametersCallback {

        void onParametersLoaded(@NonNull List<Parameter> parameters);

        void onDataNotAvailable();
    }

    interface GetParameterCallback {

        void onParameterLoaded(@NonNull Parameter parameter);

        void onDataNotAvailable();
    }

    void saveParameter(@NonNull Parameter parameter);

    void getParameter(@NonNull String id, @NonNull GetParameterCallback callback);

    void getParameters(@NonNull GetParametersCallback callback);

    void deleteParameter(@NonNull String id);

    void deleteAllParameters();

    void updateParameter(@NonNull Parameter suggestion);

}

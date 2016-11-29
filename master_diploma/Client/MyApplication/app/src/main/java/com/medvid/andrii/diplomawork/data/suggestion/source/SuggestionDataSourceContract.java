package com.medvid.andrii.diplomawork.data.suggestion.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 * <p>
 * For simplicity, only getTasks() and getTask() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
 */

public interface SuggestionDataSourceContract {

    interface GetSuggestionsCallback {

        void onSuggestionsLoaded(@NonNull List<Suggestion> suggestions);

        void onDataNotAvailable();
    }

    interface GetSuggestionCallback {

        void onSuggestionLoaded(@NonNull Suggestion suggestion);

        void onDataNotAvailable();
    }

    /**
     *
     * @param suggestion
     * @return inserted row id
     */
    long saveSuggestion(@NonNull Suggestion suggestion);

    /**
     *
     * @param suggestionList
     * @return inserted rows count
     */
    int saveSuggestions(@NonNull List<Suggestion> suggestionList);

    void getSuggestion(@NonNull String id, @NonNull GetSuggestionCallback callback);

    void getSuggestions(@NonNull GetSuggestionsCallback callback);

    void deleteSuggestion(@NonNull String id);

    void deleteAllSuggestions();

    void updateSuggestion(@NonNull Suggestion suggestion);

}

package com.medvid.andrii.diplomawork.data.forecast.suggestion;

import android.support.annotation.NonNull;

public class SuggestionLocalDataSource implements SuggestionDataSourceContract {

    @Override
    public void saveSuggestion(@NonNull Suggestion suggestion) {

    }

    @Override
    public void getSuggestion(@NonNull String id, @NonNull GetSuggestionCallback callback) {
        //  load data via Cursor Loader //TODO
    }

    @Override
    public void getSuggestions(@NonNull GetSuggestionsCallback callback) {
        //  load data via Cursor Loader //TODO
    }

    @Override
    public void deleteSuggestion(@NonNull String id) {

    }

    @Override
    public void deleteAllSuggestions() {

    }

    @Override
    public void updateSuggestion(@NonNull Suggestion suggestion) {

    }
}

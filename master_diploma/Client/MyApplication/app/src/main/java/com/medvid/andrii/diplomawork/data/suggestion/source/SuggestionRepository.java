package com.medvid.andrii.diplomawork.data.suggestion.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;

public class SuggestionRepository   implements SuggestionDataSourceContract {

    @Override
    public void saveSuggestion(@NonNull Suggestion suggestion) {

    }

    @Override
    public void getSuggestion(@NonNull String id, @NonNull GetSuggestionCallback callback) {

    }

    @Override
    public void getSuggestions(@NonNull GetSuggestionsCallback callback) {

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

package com.medvid.andrii.diplomawork.data.suggestion.source;

import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.suggestion.Suggestion;

import java.util.List;

public class SuggestionRemoteDataSource implements SuggestionDataSourceContract {

    @Override
    public long saveSuggestion(@NonNull Suggestion suggestion) {
        return 0;
    }

    @Override
    public int saveSuggestions(@NonNull List<Suggestion> suggestionList) {
        return 0;
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

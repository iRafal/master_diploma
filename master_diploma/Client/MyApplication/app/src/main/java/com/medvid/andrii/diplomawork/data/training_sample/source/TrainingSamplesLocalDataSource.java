package com.medvid.andrii.diplomawork.data.training_sample.source;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.training_sample.TrainingSample;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSampleTableContract;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.google.common.base.Preconditions.checkNotNull;

public class TrainingSamplesLocalDataSource implements TrainingSampleDataSourceContract {

    private static TrainingSamplesLocalDataSource INSTANCE = null;

    private ContentResolver mContentResolver;

    public static TrainingSamplesLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new TrainingSamplesLocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private TrainingSamplesLocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        mContentResolver = contentResolver;
    }

    /**
     * {@link TrainingSampleDataSourceContract } methods implementation
     */

    @Override
    public void saveTrainingSample(@NonNull TrainingSample trainingSample) {
        checkNotNull(trainingSample);
        ContentValues contentValues = TrainingSampleTableContract.getInstance().getContentValues(trainingSample);
        mContentResolver.insert(TrainingSampleTableContract.buildUri(), contentValues);
    }

    @Override
    public long saveTrainingSamples(@NonNull List<TrainingSample> list) {
        checkNotNull(list);

        List<ContentValues> contentValuesList = new ArrayList<>(list.size());
        for (TrainingSample listItem : list) {
            contentValuesList.add(TrainingSampleTableContract.getInstance().getContentValues(listItem));
        }

        return mContentResolver.bulkInsert(TrainingSampleTableContract.buildUri(),
                contentValuesList.toArray(new ContentValues[]{}));
    }

    @Override
    public void getTrainingSample(@NonNull String id, @NonNull GetTrainingSampleCallback callback) {
        //  load data via Cursor Loader //TODO

        checkNotNull(id);
        checkNotNull(callback);

        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor cursor = mContentResolver.query(
                TrainingSampleTableContract.buildUriWith(id),
                TrainingSampleTableContract.getInstance().getColumns(),
                selection, selectionArgs, null);
        TrainingSample trainingSample = null;
        if (cursor != null && cursor.moveToFirst()) {
            trainingSample = TrainingSampleTableContract.getInstance().getEntity(cursor);
            cursor.close();
        }
        callback.onTrainingSampleLoaded(trainingSample);
    }

    @Override
    public void getTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {
        //  load data via Cursor Loader //TODO

        String selection = String.format("%s LIKE ? AND %s LIKE ?",
                TrainingSampleTableContract.IS_STATISTICS,
                TrainingSampleTableContract.IS_FORECAST);
        String[] selectionArgs = {Long.toString(0), Long.toString(0)};
        String sortOrder = String.format("%s DESC", TrainingSampleTableContract.TIME_STAMP);
        getTrainingSamples(selection, selectionArgs, sortOrder, callback);
    }

    @Override
    public void getForecastTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {

        String selection = String.format("%s LIKE ? AND %s LIKE ?",
                TrainingSampleTableContract.IS_STATISTICS,
                TrainingSampleTableContract.IS_FORECAST);
        String[] selectionArgs = {Long.toString(0), Long.toString(1)};
        String sortOrder = String.format("%s DESC", TrainingSampleTableContract.TIME_STAMP);
        getTrainingSamples(selection, selectionArgs, sortOrder, callback);
    }

    @Override
    public void getStatisticsTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {

        String selection = String.format("%s LIKE ? AND %s LIKE ?",
                TrainingSampleTableContract.IS_STATISTICS,
                TrainingSampleTableContract.IS_FORECAST);
        String[] selectionArgs = {Long.toString(1), Long.toString(0)};
        String sortOrder = String.format("%s DESC", TrainingSampleTableContract.TIME_STAMP);
        getTrainingSamples(selection, selectionArgs, sortOrder, callback);
    }

    @Override
    public void deleteTrainingSample(@NonNull String id) {
        checkNotNull(id);
        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {id};
        mContentResolver.delete(TrainingSampleTableContract.buildUri(), selection, selectionArgs);
    }

    @Override
    public void deleteAllTrainingSamples() {
        mContentResolver.delete(TrainingSampleTableContract.buildUri(), null, null);
    }

    @Override
    public void updateTrainingSample(@NonNull TrainingSample trainingSample) {
        checkNotNull(trainingSample);

        ContentValues values = TrainingSampleTableContract.getInstance().getContentValues(trainingSample);

        String selection = _ID + " LIKE ?";
        String[] selectionArgs = {Long.toString(trainingSample.getId())};

        mContentResolver.update(TrainingSampleTableContract.buildUri(), values, selection, selectionArgs);
    }

    public void getTrainingSamples(
            @NonNull String selection,
            @NonNull String[] selectionArgs,
            @NonNull String sortOrder,
            @NonNull GetTrainingSamplesCallback callback) {

        checkNotNull(callback);

        Cursor cursor = mContentResolver.query(
                TrainingSampleTableContract.buildUri(),
                TrainingSampleTableContract.getInstance().getColumns(),
                selection,
                selectionArgs,
                sortOrder);

        TrainingSample trainingSample = null;
        List<TrainingSample> list = new ArrayList<>();

        if (cursor == null || !cursor.moveToFirst()) {
            cursor.close();
            callback.onTrainingSamplesLoaded(list);
            return;
        }

        do {
            trainingSample = TrainingSampleTableContract.getInstance().getEntity(cursor);
            list.add(trainingSample);
        } while (cursor.moveToNext());

        cursor.close();
        callback.onTrainingSamplesLoaded(list);
    }

}

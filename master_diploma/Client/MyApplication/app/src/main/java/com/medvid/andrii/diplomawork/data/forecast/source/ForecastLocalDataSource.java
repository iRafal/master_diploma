package com.medvid.andrii.diplomawork.data.forecast.source;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.medvid.andrii.diplomawork.data.forecast.Forecast;
import com.medvid.andrii.diplomawork.data.forecast.ForecastTableContract;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ForecastLocalDataSource implements ForecastDataSourceContract  {

    private static ForecastLocalDataSource INSTANCE = null;

    private ContentResolver mContentResolver;

    public static ForecastLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new ForecastLocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    /**
     * Constructor is private to prevent direct instantiation
     */
    private ForecastLocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        mContentResolver = contentResolver;
    }

    /**
     * {@link ForecastDataSourceContract } methods implementation
     */

    @Override
    public long saveForecastSample(@NonNull Forecast forecast) {
        checkNotNull(forecast);
        ContentValues contentValues = ForecastTableContract.getInstance().getContentValues(forecast);
        Uri uri = mContentResolver.insert(ForecastTableContract.buildUri(), contentValues);
        return Long.valueOf(uri.getLastPathSegment());
    }

    @Override
    public long saveForecastSamples(@NonNull List<Forecast> forecasts) {
        checkNotNull(forecasts);

        List<ContentValues> contentValuesList = new ArrayList<>(forecasts.size());
        for (Forecast listItem : forecasts) {
            contentValuesList.add(ForecastTableContract.getInstance().getContentValues(listItem));
        }

        return mContentResolver.bulkInsert(ForecastTableContract.buildUri(), contentValuesList.toArray(new ContentValues[]{}));
    }

    @Override
    public void getForecastSample(long id, @NonNull GetForecastSampleCallback callback) {
        //  load data via Cursor Loader //TODO
        checkNotNull(callback);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { Long.toString(id) };

        Cursor cursor = mContentResolver.query(
                ForecastTableContract.buildUriWith(id),
                ForecastTableContract.getInstance().getColumns(),
                selection, selectionArgs, null);
        Forecast forecast = null;
        if (cursor != null && cursor.moveToFirst()) {
            forecast = ForecastTableContract.getInstance().getEntity(cursor);
            cursor.close();
        }
        callback.onForecastSampleLoaded(forecast);
    }

    @Override
    public void getForecastSamples(@NonNull GetForecastSamplesCallback callback) {
        //  load data via Cursor Loader //TODO
        checkNotNull(callback);

        Cursor cursor = mContentResolver.query(
                ForecastTableContract.buildUri(),
                ForecastTableContract.getInstance().getColumns(),
                null, null, null);

        Forecast forecast = null;
        List<Forecast> forecastList = new ArrayList<>();

        if (cursor == null || !cursor.moveToFirst())	{
            cursor.close();
            callback.onForecastsLoaded(forecastList);
            return;
        }

        do {
            forecast = ForecastTableContract.getInstance().getEntity(cursor);
            forecastList.add(forecast);
        } while (cursor.moveToNext());

        cursor.close();
        callback.onForecastsLoaded(forecastList);
    }

    @Override
    public void deleteForecastSample(@NonNull String id) {
        checkNotNull(id);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {id};

        mContentResolver.delete(ForecastTableContract.buildUriWith(id), selection, selectionArgs);
    }

    @Override
    public void deleteAllForecastSamples() {
        mContentResolver.delete(ForecastTableContract.buildUri(), null, null);
    }

    @Override
    public void updateForecastSample(@NonNull Forecast forecast) {
        checkNotNull(forecast);

        ContentValues values = ForecastTableContract.getInstance().getContentValues(forecast);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {  Long.toString(forecast.getId())  };

        mContentResolver.update(ForecastTableContract.buildUri(), values, selection, selectionArgs);
    }
}

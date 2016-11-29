    package com.medvid.andrii.diplomawork.data.training_sample;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

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
        public void getTrainingSample(@NonNull String id, @NonNull GetTrainingSampleCallback callback) {
            //  load data via Cursor Loader //TODO
        }

        @Override
        public void getTrainingSamples(@NonNull GetTrainingSamplesCallback callback) {
            //  load data via Cursor Loader //TODO
        }

        @Override
        public void deleteTrainingSample(@NonNull String id) {
            checkNotNull(id);

            String selection = BaseColumns._ID + " LIKE ?";
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

            String selection = BaseColumns._ID + " LIKE ?";
            String[] selectionArgs = {  Long.toString(trainingSample.getId())  };

            mContentResolver.update(TrainingSampleTableContract.buildUri(), values, selection, selectionArgs);
        }
    }

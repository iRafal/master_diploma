/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.medvid.andrii.diplomawork.data;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.medvid.andrii.diplomawork.data.user.UserTableContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class UsersLoaderProvider {

    @NonNull
    private final Context mContext;

    public UsersLoaderProvider(@NonNull Context context) {
        mContext = checkNotNull(context);
    }

    public Loader<Cursor> createFilteredLoader() {
        String selection = null;
        String[] selectionArgs = null;

        return new CursorLoader(
                mContext,
                UserTableContract.buildUri(),
                UserTableContract.getInstance().getColumns(),
                selection,
                selectionArgs,
                null);
    }

    public Loader<Cursor> createLoader(String taskId) {
        return new CursorLoader(mContext, UserTableContract.buildUriWith(taskId),
                                null,
                                null,
                                new String[]{String.valueOf(taskId)},
                                null );
    }
}

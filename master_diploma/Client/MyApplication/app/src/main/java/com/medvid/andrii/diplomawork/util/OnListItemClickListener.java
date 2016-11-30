package com.medvid.andrii.diplomawork.util;

import android.support.annotation.NonNull;

public interface OnListItemClickListener<T> {
    void onListItemClick(@NonNull T object);
}
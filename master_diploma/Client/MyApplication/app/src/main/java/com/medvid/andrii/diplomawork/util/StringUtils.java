package com.medvid.andrii.diplomawork.util;

import android.support.annotation.StringRes;

import com.medvid.andrii.diplomawork.DiplomaApplication;
import com.medvid.andrii.diplomawork.R;


public class StringUtils {

    private static DiplomaApplication sApplication = DiplomaApplication.getInstance();

    public static String getEmptyFieldString(@StringRes int fieldNameStringRes)    {
        String filedName = sApplication.getString(fieldNameStringRes);
        return sApplication.getString(R.string.empty_field_message_format, filedName);
    }

    public static String getWrongFieldString(@StringRes int fieldNameStringRes)    {
        String filedName = sApplication.getString(fieldNameStringRes);
        return sApplication.getString(R.string.wrong_field_format, filedName);
    }
}

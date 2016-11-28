package com.medvid.andrii.diplomawork.util;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    /**
     * Calculates body mass index
     */
    public static double calculateBodyMassIndex(double weight, double growth)   {
        return weight / Math.pow(growth, 2);
    }

    public static Date getDateFromString(@NonNull String string)   {
        Preconditions.checkNotNull(string);

        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.ENGLISH);

        try {
           return formatter.parse(string);
        } catch (ParseException e) {
        }

        return null;
    }

    public static String getStringFromDate(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.ENGLISH);
        return formatter.format(date);
    }
}

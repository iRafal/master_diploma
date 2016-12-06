package com.medvid.andrii.diplomawork.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.format.DateFormat;

import com.google.common.base.Preconditions;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {

    /**
     * Calculates body mass index
     */
    public static double calculateBodyMassIndex(double weight, double growth)   {
        return weight / Math.pow(growth, 2);
    }

    public static Spanned fromHtml(String text)    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        }
        return Html.fromHtml(text);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static SimpleDateFormat getDateFormatterByLocale(@NonNull String format, @NonNull Locale locale)	{

        Preconditions.checkNotNull(format);
        Preconditions.checkNotNull(locale);

        SimpleDateFormat dateFormat = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            dateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, format), locale);
        } else {
            dateFormat = new SimpleDateFormat(format, locale);
        }
        return dateFormat;
    }
}

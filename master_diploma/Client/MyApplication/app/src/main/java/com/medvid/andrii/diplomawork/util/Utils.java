package com.medvid.andrii.diplomawork.util;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

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
}

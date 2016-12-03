package com.medvid.andrii.diplomawork.util;

public class Utils {

    /**
     * Calculates body mass index
     */
    public static double calculateBodyMassIndex(double weight, double growth)   {
        return weight / Math.pow(growth, 2);
    }
}

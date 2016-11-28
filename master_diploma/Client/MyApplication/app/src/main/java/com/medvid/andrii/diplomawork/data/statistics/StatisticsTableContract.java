package com.medvid.andrii.diplomawork.data.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class StatisticsTableContract implements TableDefinitionContract<Statistics> {

    public static final String TABLE_NAME = "statistics";

    public static final String DISTANCE = "distance";
    // Sleep
    public static final String SLEEP_HOURS_COUNT = "sleep_hours_count";
    public static final String SLEEP_QUALITY = "sleep_quality";

    // Calories
    public static final String SPENT_CALORIES = "spent_calories";
    public static final String EATEN_CALORIES = "eaten_calories";

    public static final String FOOD_MULTIPLICITY = "food_multiplicity";
    public static final String FAT_AMOUNT = "fat_amount";
    public static final String CARBOHYDRATE_AMOUNT = "carbohydrate_amount";
    public static final String PROTEIN_AMOUNT = "protein_amount";
    public static final String VITAMIN_C = "vitamin_c";
    public static final String SUGAR_LEVEL = "sugar_level";
    public static final String STRESS_LEVEL = "stress_level";
    public static final String TEMPERATURE = "temperature";

    // Pressure
    public static final String HIGH_PRESSURE = "high_pressure";
    public static final String LOW_PRESSURE = "low_pressure";

    public static final String PULSE = "pulse";
    public static final String TIME_STAMP = "time_stamp";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + SPACE + TABLE_NAME + SPACE + "("
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + SPACE + AUTOINCREMENT + COMMA
                    + DISTANCE + SPACE + REAL_TYPE + COMMA
                    + SLEEP_HOURS_COUNT + SPACE + REAL_TYPE + COMMA  // Sleep
                    + SLEEP_QUALITY + SPACE + REAL_TYPE + COMMA  // Sleep
                    + SPENT_CALORIES + SPACE + REAL_TYPE + COMMA    // Calories
                    + EATEN_CALORIES + SPACE + REAL_TYPE + COMMA    // Calories
                    + FOOD_MULTIPLICITY + SPACE + REAL_TYPE + COMMA
                    + FAT_AMOUNT + SPACE + REAL_TYPE + COMMA
                    + CARBOHYDRATE_AMOUNT + SPACE + REAL_TYPE + COMMA
                    + PROTEIN_AMOUNT + SPACE + REAL_TYPE + COMMA
                    + VITAMIN_C + SPACE + REAL_TYPE + COMMA
                    + SUGAR_LEVEL + SPACE + REAL_TYPE + COMMA
                    + STRESS_LEVEL + SPACE + REAL_TYPE + COMMA
                    + TEMPERATURE + SPACE + REAL_TYPE + COMMA
                    + HIGH_PRESSURE + SPACE + REAL_TYPE + COMMA // Pressure
                    + LOW_PRESSURE + SPACE + REAL_TYPE + COMMA // Pressure
                    + PULSE + SPACE + REAL_TYPE + COMMA
                    + TIME_STAMP + SPACE + REAL_TYPE
                    + SPACE + ")";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + SPACE + TABLE_NAME;

    public static final int CODE_STATISTICS = 2;
    public static final int CODE_STATISTICS_ITEM = 3;
    public static final String CONTENT_STATISTICS_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_STATISTICS_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static StatisticsTableContract getInstance()   {
        return new StatisticsTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }


    @Override
    public String[] getColumns() {
        return new String[] {
                DISTANCE,

                // Sleep
                SLEEP_HOURS_COUNT,
                SLEEP_QUALITY,

                // Calories
                SPENT_CALORIES,
                EATEN_CALORIES,

                FOOD_MULTIPLICITY,
                FAT_AMOUNT,
                CARBOHYDRATE_AMOUNT,
                PROTEIN_AMOUNT,
                VITAMIN_C,
                SUGAR_LEVEL,
                STRESS_LEVEL,
                TEMPERATURE,

                // Pressure
                HIGH_PRESSURE,
                LOW_PRESSURE,

                PULSE,
                TIME_STAMP
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Statistics statistics) {
        Preconditions.checkNotNull(statistics);

        ContentValues values = new ContentValues();

        values.put(_ID, statistics.getId());
        values.put(DISTANCE, statistics.getDistance());
        values.put(SLEEP_HOURS_COUNT, statistics.getSleep().getSleepHoursCount());
        values.put(SLEEP_QUALITY, statistics.getSleep().getSleepQuality());
        values.put(SPENT_CALORIES, statistics.getCalories().getSpentCalories());
        values.put(EATEN_CALORIES, statistics.getCalories().getEatenCalories());
        values.put(FOOD_MULTIPLICITY, statistics.getFoodMultiplicity());
        values.put(FAT_AMOUNT, statistics.getFatAmount());
        values.put(CARBOHYDRATE_AMOUNT, statistics.getCarbohydrateAmount());
        values.put(PROTEIN_AMOUNT, statistics.getProteinAmount());
        values.put(VITAMIN_C, statistics.getVitaminC());
        values.put(SUGAR_LEVEL, statistics.getSugarLevel());
        values.put(STRESS_LEVEL, statistics.getStressLevel());
        values.put(TEMPERATURE, statistics.getTemperature());
        values.put(HIGH_PRESSURE, statistics.getPressure().getHighPressure());
        values.put(LOW_PRESSURE, statistics.getPressure().getLowPressure());
        values.put(PULSE, statistics.getPulse());
        values.put(TIME_STAMP, statistics.getTimeStamp());

        return values;
    }

    @Override
    public Statistics getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int distanceIndex = cursor.getColumnIndexOrThrow(DISTANCE);
        int sleepHoursCountIndex = cursor.getColumnIndexOrThrow(SLEEP_HOURS_COUNT);
        int sleepQualityIndex = cursor.getColumnIndexOrThrow(SLEEP_QUALITY);
        int spentCaloriesIndex = cursor.getColumnIndexOrThrow(SPENT_CALORIES);
        int eatenCaloriesIndex = cursor.getColumnIndexOrThrow(EATEN_CALORIES);
        int foodMultiplicityIndex = cursor.getColumnIndexOrThrow(FOOD_MULTIPLICITY);
        int fatAmountIndex = cursor.getColumnIndexOrThrow(FAT_AMOUNT);
        int carbohydrateAmountIndex = cursor.getColumnIndexOrThrow(CARBOHYDRATE_AMOUNT);
        int proteinAmountIndex = cursor.getColumnIndexOrThrow(PROTEIN_AMOUNT);
        int vitaminCIndex = cursor.getColumnIndexOrThrow(VITAMIN_C);
        int sugarLevelIndex = cursor.getColumnIndexOrThrow(SUGAR_LEVEL);
        int stressLevelIndex = cursor.getColumnIndexOrThrow(STRESS_LEVEL);
        int temperatureIndex = cursor.getColumnIndexOrThrow(TEMPERATURE);
        int highPressureIndex = cursor.getColumnIndexOrThrow(HIGH_PRESSURE);
        int lowPressureIndex = cursor.getColumnIndexOrThrow(LOW_PRESSURE);
        int pulseIndex = cursor.getColumnIndexOrThrow(PULSE);
        int timeStampIndex = cursor.getColumnIndexOrThrow(TIME_STAMP);

        int id = cursor.getInt(idIndex);
        double distance = cursor.getDouble(distanceIndex);
        double sleepHoursCount = cursor.getDouble(sleepHoursCountIndex);
        double sleepQuality = cursor.getDouble(sleepQualityIndex);
        double spentCalories = cursor.getDouble(spentCaloriesIndex);
        double eatenCalories = cursor.getDouble(eatenCaloriesIndex);
        double foodMultiplicity = cursor.getDouble(foodMultiplicityIndex);
        double fatAmount = cursor.getDouble(fatAmountIndex);
        double carbohydrateAmount = cursor.getDouble(carbohydrateAmountIndex);
        double proteinAmount = cursor.getDouble(proteinAmountIndex);
        double vitaminC = cursor.getDouble(vitaminCIndex);
        double sugarLevel = cursor.getDouble(sugarLevelIndex);
        double stressLevel = cursor.getDouble(stressLevelIndex);
        double temperature = cursor.getDouble(temperatureIndex);
        double highPressure = cursor.getDouble(highPressureIndex);
        double lowPressure = cursor.getDouble(lowPressureIndex);
        double pulse = cursor.getDouble(pulseIndex);
        long timeStamp = cursor.getLong(timeStampIndex);

        return new Statistics(id,
                distance, new Sleep(sleepHoursCount, sleepQuality),
                new Calories(spentCalories, eatenCalories),
                foodMultiplicity,
                fatAmount,
                carbohydrateAmount,
                proteinAmount,
                vitaminC,
                sugarLevel,
                stressLevel,
                temperature,
                new Pressure(highPressure, lowPressure),
                pulse,
                timeStamp);
    }
}

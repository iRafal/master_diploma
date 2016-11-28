package com.medvid.andrii.diplomawork.data.training_sample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Calories;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Pressure;
import com.medvid.andrii.diplomawork.data.training_sample.entities.Sleep;
import com.medvid.andrii.diplomawork.data.user.User;
import com.medvid.andrii.diplomawork.util.Utils;

import java.util.Date;

public class TrainingSampleTableContract implements TableDefinitionContract<TrainingSample> {

    public static final String TABLE_NAME = "training_sample";

    // Static user info
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String GROWTH = "growth";
    public static final String WEIGHT = "weight";
    public static final String BODY_MASS_INDEX = "body_mass_index";
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
    public static final String IS_FORECAST = "is_forecast";
    public static final String IS_STATISTICS = "is_statistics";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ( "
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + " " + AUTOINCREMENT + ", "
                    + AGE + " " + REAL_TYPE + ", " // Static user info
                    + GENDER + " " + REAL_TYPE + ", " // Static user info
                    + GROWTH + " " + REAL_TYPE + ", " // Static user info
                    + WEIGHT + " " + REAL_TYPE + ", " // Static user info
                    + BODY_MASS_INDEX + " " + REAL_TYPE + ", " // Static user info
                    + DISTANCE + " " + REAL_TYPE + ", "
                    + SLEEP_HOURS_COUNT + " " + REAL_TYPE + ", "  // Sleep
                    + SLEEP_QUALITY + " " + REAL_TYPE + ", " // Sleep
                    + SPENT_CALORIES + " " + REAL_TYPE + ", "    // Calories
                    + EATEN_CALORIES + " " + REAL_TYPE + ", "    // Calories
                    + FOOD_MULTIPLICITY + " " + REAL_TYPE + ", "
                    + FAT_AMOUNT + " " + REAL_TYPE + ", "
                    + CARBOHYDRATE_AMOUNT + " " + REAL_TYPE + ", "
                    + PROTEIN_AMOUNT + " " + REAL_TYPE + ", "
                    + VITAMIN_C + " " + REAL_TYPE + ", "
                    + SUGAR_LEVEL + " " + REAL_TYPE + ", "
                    + STRESS_LEVEL + " " + REAL_TYPE + ", "
                    + TEMPERATURE + " " + REAL_TYPE + ", "
                    + HIGH_PRESSURE + " " + REAL_TYPE + ", " // Pressure
                    + LOW_PRESSURE + " " + REAL_TYPE + ", "// Pressure
                    + PULSE + " " + REAL_TYPE + ", "
                    + TIME_STAMP + " " + REAL_TYPE + ", "
                    + IS_FORECAST + " " + BOOLEAN_TYPE + ", "
                    + IS_STATISTICS + " " + BOOLEAN_TYPE
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_TRAINING_SAMPLE = 2;
    public static final int CODE_TRAINING_SAMPLE_ITEM = 3;
    public static final String CONTENT_TRAINING_SAMPLE_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_TRAINING_SAMPLE_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static TrainingSampleTableContract getInstance()   {
        return new TrainingSampleTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }


    @Override
    public String[] getColumns() {
        return new String[] {
                // Static user info
                AGE,
                GENDER,
                GROWTH,
                WEIGHT,
                BODY_MASS_INDEX,

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
                TIME_STAMP,
                IS_FORECAST,
                IS_STATISTICS
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull TrainingSample trainingSample) {
        Preconditions.checkNotNull(trainingSample);

        ContentValues values = new ContentValues();

        values.put(_ID, trainingSample.getId());
        values.put(AGE, trainingSample.getAge());
        values.put(GENDER, trainingSample.getGender());
        values.put(GROWTH, trainingSample.getGrowth());
        values.put(WEIGHT, trainingSample.getWeight());
        values.put(BODY_MASS_INDEX, trainingSample.getBodyMassIndex());
        values.put(DISTANCE, trainingSample.getDistance());
        values.put(SLEEP_HOURS_COUNT, trainingSample.getSleep().getSleepHoursCount());
        values.put(SLEEP_QUALITY, trainingSample.getSleep().getSleepQuality());
        values.put(SPENT_CALORIES, trainingSample.getCalories().getSpentCalories());
        values.put(EATEN_CALORIES, trainingSample.getCalories().getEatenCalories());
        values.put(FOOD_MULTIPLICITY, trainingSample.getFoodMultiplicity());
        values.put(FAT_AMOUNT, trainingSample.getFatAmount());
        values.put(CARBOHYDRATE_AMOUNT, trainingSample.getCarbohydrateAmount());
        values.put(PROTEIN_AMOUNT, trainingSample.getProteinAmount());
        values.put(VITAMIN_C, trainingSample.getVitaminC());
        values.put(SUGAR_LEVEL, trainingSample.getSugarLevel());
        values.put(STRESS_LEVEL, trainingSample.getStressLevel());
        values.put(TEMPERATURE, trainingSample.getTemperature());
        values.put(HIGH_PRESSURE, trainingSample.getPressure().getHighPressure());
        values.put(LOW_PRESSURE, trainingSample.getPressure().getLowPressure());
        values.put(PULSE, trainingSample.getPulse());
        values.put(TIME_STAMP, Utils.getStringFromDate(trainingSample.getTimeStamp()));
        values.put(IS_FORECAST, trainingSample.isForecast());
        values.put(IS_STATISTICS, trainingSample.isStatistics());

        return values;
    }

    @Override
    public TrainingSample getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(BaseColumns._ID);
        int distanceIndex = cursor.getColumnIndexOrThrow(DISTANCE);
        int ageIndex = cursor.getColumnIndexOrThrow(AGE);
        int genderIndex = cursor.getColumnIndexOrThrow(GENDER);
        int growthIndex = cursor.getColumnIndexOrThrow(GROWTH);
        int weightIndex = cursor.getColumnIndexOrThrow(WEIGHT);
        int bodyMassIndexValue = cursor.getColumnIndexOrThrow(BODY_MASS_INDEX);
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
        int isForecastIndex = cursor.getColumnIndexOrThrow(IS_FORECAST);
        int isStatisticsIndex = cursor.getColumnIndexOrThrow(IS_STATISTICS);


        int id = cursor.getInt(idIndex);
        double distance = cursor.getDouble(distanceIndex);
        double age = cursor.getDouble(ageIndex);
        @User.Gender int gender = cursor.getInt(genderIndex);
        double growth = cursor.getDouble(growthIndex);
        double weight = cursor.getDouble(weightIndex);
        double bodyMassIndex = cursor.getDouble(bodyMassIndexValue);
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
        Date timeStamp = Utils.getDateFromString(cursor.getString(timeStampIndex));
        boolean isForecast = cursor.getInt(isForecastIndex) == 1;
        boolean isStatistics = cursor.getInt(isStatisticsIndex) == 1;

        return new TrainingSample(id,
                age,
                gender,
                growth,
                weight,
                bodyMassIndex,
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
                timeStamp,
                isForecast,
                isStatistics);
    }
}

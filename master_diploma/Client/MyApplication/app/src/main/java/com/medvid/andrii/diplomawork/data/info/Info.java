package com.medvid.andrii.diplomawork.data.info;

import android.support.annotation.IntDef;

import com.medvid.andrii.diplomawork.data.ModelEntity;

import java.lang.annotation.Retention;

import static com.medvid.andrii.diplomawork.data.info.Info.Gender.MAN;
import static com.medvid.andrii.diplomawork.data.info.Info.Gender.WOMEN;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Info implements ModelEntity {

    @Retention(SOURCE)
    @IntDef({MAN, WOMEN})
    public @interface Gender {
        int MAN = 0;
        int WOMEN = 1;
    }

    int age; //18
    @Info.Gender int gender; // 1
    double growth; // 1.8
    double weight; // 75
    int bodeMassIndex; // 20
    double distance; //  4
    double sleepHoursCount; //
    double sleepQuality; //8
    double spentCalories; // 2500
    double eatenCalories; //3000

    double foodMultiplicity; //3
    double fatAmount; // 75
    double carbohydrateAmount; // 200
    double proteinAmount; //  85
    int vitaminC; //  60
    int sugarLevel; //  3
    int stressLevel; //  3
    double temperature; //  36.6

    double hightPressure; //  120
    double lowPressure; //  90
    double pulse; // 60
}

package com.medvid.andrii.diplomawork.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.medvid.andrii.diplomawork.data.disease.DiseaseTableContract;
import com.medvid.andrii.diplomawork.data.group_risk.GroupRiskTableContract;
import com.medvid.andrii.diplomawork.data.suggestion.SuggestionTableContract;
import com.medvid.andrii.diplomawork.data.suggestion.parameter.ParameterTableContract;
import com.medvid.andrii.diplomawork.data.training_sample.TrainingSampleTableContract;
import com.medvid.andrii.diplomawork.data.user.UserTableContract;

public class MasterDiplomaDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MasterDiploma.db";
    public static final int DB_VERSION = 2;

    public MasterDiplomaDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(TrainingSampleTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(DiseaseTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(GroupRiskTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(ParameterTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(SuggestionTableContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(UserTableContract.DROP_TABLE);
        sqLiteDatabase.execSQL(TrainingSampleTableContract.DROP_TABLE);
        sqLiteDatabase.execSQL(SuggestionTableContract.DROP_TABLE);
//        sqLiteDatabase.execSQL(DiseaseTableContract.DROP_TABLE);
//        sqLiteDatabase.execSQL(ParameterTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(GroupRiskTableContract.DROP_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        super.onDowngrade(sqLiteDatabase, oldVersion, newVersion);
        sqLiteDatabase.execSQL(UserTableContract.DROP_TABLE);
        sqLiteDatabase.execSQL(TrainingSampleTableContract.DROP_TABLE);
        sqLiteDatabase.execSQL(SuggestionTableContract.DROP_TABLE);
//        sqLiteDatabase.execSQL(DiseaseTableContract.DROP_TABLE);
//        sqLiteDatabase.execSQL(ParameterTableContract.CREATE_TABLE);
        sqLiteDatabase.execSQL(GroupRiskTableContract.DROP_TABLE);
    }
}

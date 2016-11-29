package com.medvid.andrii.diplomawork.data.forecast;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class ForecastTableContract implements TableDefinitionContract<Forecast> {

    public static final String TABLE_NAME = "forecast";

    public static final String DISEASE_ID = "disease_id";
    public static final String DISEASE_NAME = "disease_name";

    public static final String GROUP_RISK_ID = "group_risk_id";
    public static final String GROUP_RISK_NAME = "group_risk_name";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ( "
                    + _ID + " " + INTEGER_TYPE + " " + NOT_NULL + " " + PRIMARY_KEY + " " + AUTOINCREMENT + ", "
                    + DISEASE_ID + " " + INTEGER_TYPE + " " + NOT_NULL + ", "
                    + DISEASE_NAME + " " + TEXT_TYPE + " " + NOT_NULL + ", "
                    + GROUP_RISK_ID + " " + INTEGER_TYPE + " " + NOT_NULL + ", "
                    + GROUP_RISK_NAME + " " + TEXT_TYPE + " " + NOT_NULL
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_FORECAST = 6;
    public static final int CODE_FORECAST_ITEM = 7;
    public static final String CONTENT_FORECAST_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_FORECAST_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static ForecastTableContract getInstance() {
        return new ForecastTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    public static Uri buildUriWith(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    public static Uri buildUriWith(String id) {
        return CONTENT_URI.buildUpon().appendPath(id).build();
    }

    private ForecastTableContract() {

    }

    @Override
    public String[] getColumns() {
        return new String[]{
                _ID,
                DISEASE_ID,
                DISEASE_NAME,
                GROUP_RISK_ID,
                GROUP_RISK_NAME
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Forecast modelEntity) {

        Preconditions.checkNotNull(modelEntity);

        ContentValues values = new ContentValues();
        values.put(_ID, modelEntity.getId());
        values.put(DISEASE_ID, modelEntity.getDisease().getId());
        values.put(DISEASE_NAME, modelEntity.getDisease().getDiseaseName());
        values.put(GROUP_RISK_ID, modelEntity.getGroupRisk().getId());
        values.put(GROUP_RISK_NAME, modelEntity.getGroupRisk().getRiskName());
        return values;
    }

    @Override
    public Forecast getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int diseaseIdIndex = cursor.getColumnIndexOrThrow(DISEASE_ID);
        int diseaseNameIndex = cursor.getColumnIndexOrThrow(DISEASE_NAME);
        int groupRiskIdIndex = cursor.getColumnIndexOrThrow(GROUP_RISK_ID);
        int groupRiskNameIndex = cursor.getColumnIndexOrThrow(GROUP_RISK_NAME);

        int id = cursor.getInt(idIndex);
        long diseaseId = cursor.getLong(diseaseIdIndex);
        String diseaseName = cursor.getString(diseaseNameIndex);
        long groupRiskId = cursor.getLong(groupRiskIdIndex);
        String groupRiskName = cursor.getString(groupRiskNameIndex);

        return new Forecast(id, new Disease(diseaseId, diseaseName), new GroupRisk(groupRiskId, groupRiskName), null);
    }

}

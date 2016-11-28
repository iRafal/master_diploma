package com.medvid.andrii.diplomawork.data.group_risk;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class GroupRiskTableContract implements TableDefinitionContract<GroupRisk> {

    public static final String TABLE_NAME = "group_risk";

    public static final String RISK_NAME = "risk_name";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + SPACE + TABLE_NAME + SPACE + "("
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + COMMA
                    + RISK_NAME + SPACE + TEXT_TYPE + COMMA
                    + SPACE + ")";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + SPACE + TABLE_NAME;

    public static final int CODE_GROUP_RISK = 0;
    public static final int CODE_GROUP_RISK_ITEM = 1;
    public static final String CONTENT_GROUP_RISK_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_GROUP_RISK_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static GroupRiskTableContract getInstance()   {
        return new GroupRiskTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    private GroupRiskTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                RISK_NAME
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull GroupRisk groupRisk) {
        Preconditions.checkNotNull(groupRisk);

        ContentValues values = new ContentValues();
        values.put(_ID, groupRisk.getId());
        values.put(RISK_NAME, groupRisk.getRiskName());

        return values;
    }

    @Override
    public GroupRisk getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int riskNameIndex = cursor.getColumnIndexOrThrow(RISK_NAME);

        int id = cursor.getInt(idIndex);
        String riskName = cursor.getString(riskNameIndex);

        return new GroupRisk(id, riskName);
    }
}

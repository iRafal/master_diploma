package com.medvid.andrii.diplomawork.data.disease;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class DiseaseTableContract implements TableDefinitionContract<Disease> {

    public static final String TABLE_NAME = "disease";

    public static final String DISEASE_NAME = "disease_name";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " ( "
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + " " + AUTOINCREMENT + ", "
                    + DISEASE_NAME + " " + TEXT_TYPE
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_DISEASE = 4;
    public static final int CODE_DISEASE_ITEM = 5;
    public static final String CONTENT_DISEASE_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_DISEASE_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static DiseaseTableContract getInstance()   {
        return new DiseaseTableContract();
    }

    public static Uri buildUri() {
        return CONTENT_URI.buildUpon().build();
    }

    private DiseaseTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                DISEASE_NAME
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull Disease disease) {
        Preconditions.checkNotNull(disease);

        ContentValues values = new ContentValues();
        values.put(_ID, disease.getId());
        values.put(DISEASE_NAME, disease.getDisseaseName());

        return values;
    }

    @Override
    public Disease getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int diseaseNameIndex = cursor.getColumnIndexOrThrow(DISEASE_NAME);

        int id = cursor.getInt(idIndex);
        String diseaseName = cursor.getString(diseaseNameIndex);

        return new Disease(id, diseaseName);
    }
}

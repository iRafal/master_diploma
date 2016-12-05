package com.medvid.andrii.diplomawork.data.user;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.data.TableDefinitionContract;

public class UserTableContract implements TableDefinitionContract<User> {

    public static final String TABLE_NAME = "user";

    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String GROWTH = "growth";
    public static final String WEIGHT = "weight";
    public static final String BODY_MASS_INDEX = "body_mass_index";
    public static final String CALORIES_PER_HOUR_TRAINING = "calories_per_hour_training";

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + " " + TABLE_NAME + " " + "(" + " "
                    + _ID + " " + INTEGER_TYPE + " " + NOT_NULL + " " + UNIQUE + " " + PRIMARY_KEY + " " + AUTOINCREMENT + ", "
                    + EMAIL + " " + TEXT_TYPE + " " + NOT_NULL + " " + UNIQUE + ", "
                    + FIRST_NAME + " " + TEXT_TYPE + " " + NOT_NULL + ", "
                    + LAST_NAME + " " + TEXT_TYPE + " " + NOT_NULL + ", "
                    + AGE + " " + REAL_TYPE + ", "
                    + GENDER + " " + REAL_TYPE + ", "
                    + GROWTH + " " + REAL_TYPE + ", "
                    + WEIGHT + " " + REAL_TYPE + ", "
                    + BODY_MASS_INDEX + " " + REAL_TYPE + ", "
                    + CALORIES_PER_HOUR_TRAINING + " " + REAL_TYPE
                    + " )";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + " " + TABLE_NAME;

    public static final int CODE_USER = 0;
    public static final int CODE_USER_ITEM = 1;
    public static final String CONTENT_USER_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final String CONTENT_USER_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

    public static UserTableContract getInstance()   {
        return new UserTableContract();
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

    private UserTableContract()  {

    }

    @Override
    public String[] getColumns() {
        return new String[] {
                BaseColumns._ID,
                EMAIL,
                FIRST_NAME,
                LAST_NAME,
                AGE,
                GENDER,
                GROWTH,
                WEIGHT,
                BODY_MASS_INDEX,
                CALORIES_PER_HOUR_TRAINING
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull User user) {
        Preconditions.checkNotNull(user);

        ContentValues values = new ContentValues();
        values.put(_ID, user.getId());
        values.put(EMAIL, user.getEmail());
        values.put(FIRST_NAME, user.getFirstName());
        values.put(LAST_NAME, user.getLastName());
        values.put(AGE, user.getAge());
        values.put(GENDER, user.getGender());
        values.put(GROWTH, user.getGrowth());
        values.put(WEIGHT, user.getWeight());
        values.put(BODY_MASS_INDEX, user.getBodyMassIndex());
        values.put(CALORIES_PER_HOUR_TRAINING, user.getCaloriesPerHourTraining());

        return values;
    }

    @Override
    public User getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(_ID);
        int emailIndex = cursor.getColumnIndexOrThrow(EMAIL);
        int firstNameIndex = cursor.getColumnIndexOrThrow(FIRST_NAME);
        int lastNameIndex = cursor.getColumnIndexOrThrow(LAST_NAME);
        int ageIndex = cursor.getColumnIndexOrThrow(AGE);
        int genderIndex = cursor.getColumnIndexOrThrow(GENDER);
        int growthIndex = cursor.getColumnIndexOrThrow(GROWTH);
        int weightIndex = cursor.getColumnIndexOrThrow(WEIGHT);
        int bodyMassIndexValue = cursor.getColumnIndexOrThrow(BODY_MASS_INDEX);
        int caloriesPerHourTrainingIndexValue = cursor.getColumnIndexOrThrow(CALORIES_PER_HOUR_TRAINING);

        int id = cursor.getInt(idIndex);
        String email = cursor.getString(emailIndex);
        String firstName = cursor.getString(firstNameIndex);
        String lastName = cursor.getString(lastNameIndex);
        double age = cursor.getDouble(ageIndex);
        @User.Gender int gender = cursor.getInt(genderIndex);
        double growth = cursor.getDouble(growthIndex);
        double weight = cursor.getDouble(weightIndex);
        double bodyMassIndex = cursor.getDouble(bodyMassIndexValue);
        double caloriesPerHourTraining = cursor.getDouble(caloriesPerHourTrainingIndexValue);


        return new User(id, email, firstName, lastName, age, gender,
                growth, weight, bodyMassIndex, caloriesPerHourTraining);
    }
}

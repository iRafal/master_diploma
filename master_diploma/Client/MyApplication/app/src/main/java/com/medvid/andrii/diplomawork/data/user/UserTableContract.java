package com.medvid.andrii.diplomawork.data.user;

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

    public static final String CREATE_TABLE =
            CREATE_TABLE_IF_NOT_EXISTS + SPACE + TABLE_NAME + SPACE + "("
                    + INTEGER_PRIMARY_KEY_ROW_DEFINITION + COMMA
                    + EMAIL + SPACE + TEXT_TYPE + COMMA
                    + FIRST_NAME + SPACE + TEXT_TYPE + COMMA
                    + AGE + SPACE + REAL_TYPE + COMMA
                    + GENDER + SPACE + REAL_TYPE + COMMA
                    + GROWTH + SPACE + REAL_TYPE + COMMA
                    + WEIGHT + SPACE + REAL_TYPE + COMMA
                    + BODY_MASS_INDEX + SPACE + REAL_TYPE
                    + SPACE + ")";

    public static final String DROP_TABLE = DROP_TABLE_IF_EXISTS + SPACE + TABLE_NAME;

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
                BODY_MASS_INDEX
        };
    }

    @Override
    public ContentValues getContentValues(@NonNull User user) {
        Preconditions.checkNotNull(user);

        ContentValues values = new ContentValues();
        values.put(BaseColumns._ID, user.getId());
        values.put(UserTableContract.EMAIL, user.getEmail());
        values.put(UserTableContract.FIRST_NAME, user.getFirstName());
        values.put(UserTableContract.LAST_NAME, user.getLastName());
        values.put(UserTableContract.AGE, user.getAge());
        values.put(UserTableContract.GENDER, user.getGender());
        values.put(UserTableContract.GROWTH, user.getGrowth());
        values.put(UserTableContract.WEIGHT, user.getWeight());
        values.put(UserTableContract.BODY_MASS_INDEX, user.getBodyMassIndex());

        return values;
    }

    @Override
    public User getEntity(@NonNull Cursor cursor) {
        Preconditions.checkNotNull(cursor);

        int idIndex = cursor.getColumnIndexOrThrow(BaseColumns._ID);
        int emailIndex = cursor.getColumnIndexOrThrow(UserTableContract.EMAIL);
        int firstNameIndex = cursor.getColumnIndexOrThrow(UserTableContract.FIRST_NAME);
        int lastNameIndex = cursor.getColumnIndexOrThrow(UserTableContract.LAST_NAME);
        int ageIndex = cursor.getColumnIndexOrThrow(UserTableContract.AGE);
        int genderIndex = cursor.getColumnIndexOrThrow(UserTableContract.GENDER);
        int growthIndex = cursor.getColumnIndexOrThrow(UserTableContract.GROWTH);
        int weightIndex = cursor.getColumnIndexOrThrow(UserTableContract.WEIGHT);
        int bodyMassIndexValue = cursor.getColumnIndexOrThrow(UserTableContract.BODY_MASS_INDEX);

        int id = cursor.getInt(idIndex);
        String email = cursor.getString(emailIndex);
        String firstName = cursor.getString(firstNameIndex);
        String lastName = cursor.getString(lastNameIndex);
        double age = cursor.getDouble(ageIndex);
        @User.Gender int gender = cursor.getInt(genderIndex);
        double growth = cursor.getDouble(growthIndex);
        double weight = cursor.getDouble(weightIndex);
        double bodyMassIndex = cursor.getDouble(bodyMassIndexValue);


        return new User(id, email, firstName, lastName, age, gender, growth, weight, bodyMassIndex);
    }
}

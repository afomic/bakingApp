package com.afomic.bakingapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by afomic on 6/18/17.
 *
 */

public class FoodContract {
    public static class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME="food_table";
        public static final String COLUMN_FOOD_ID="id";
        public static final String COLUMN_NAME="name";

    }
    public static void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ FoodContract.FoodEntry.TABLE_NAME+" ( ";
        sql+= FoodContract.FoodEntry.COLUMN_FOOD_ID+" INTEGER,";
        sql+= FoodContract.FoodEntry.COLUMN_NAME+" VARCHAR(30))";
        db.execSQL(sql);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable="DROP TABLE"+ FoodContract.FoodEntry.TABLE_NAME;
        db.execSQL(dropTable);
    }
}

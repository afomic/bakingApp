package com.afomic.bakingapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by afomic on 6/18/17.
 *
 */

public class FoodIngredientContract {
    public static class FoodIngredient implements BaseColumns {
        public static final String TABLE_NAME="food_ingredient_table";
        public static final String COLUMN_INGREDIENT_ID="id";
        public static final String COLUMN_FOOD_ID="food_id";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_MEASURE="measurement";
        public static final String COLUMN_QUANTITY="quantity";

    }
    public static void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ FoodIngredient.TABLE_NAME+" ( ";
        sql+= FoodIngredient.COLUMN_INGREDIENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql+= FoodIngredient.COLUMN_FOOD_ID+" INTEGER, ";
        sql+= FoodIngredient.COLUMN_NAME+" VARCHAR(30), ";
        sql+= FoodIngredient.COLUMN_MEASURE+" VARCHAR(5), ";
        sql+= FoodIngredient.COLUMN_QUANTITY+" REAL ) ";
        db.execSQL(sql);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable="DROP TABLE"+ FoodIngredient.TABLE_NAME;
        db.execSQL(dropTable);
    }
}

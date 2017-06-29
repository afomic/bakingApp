package com.afomic.bakingapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by afomic on 6/18/17.
 *
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="baking";
    private static final int DB_VERSION=1;
    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        FoodStepContract.onCreate(sqLiteDatabase);
        FoodContract.onCreate(sqLiteDatabase);
        FoodIngredientContract.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        FoodStepContract.onUpgrade(sqLiteDatabase,i,i1);
        FoodContract.onUpgrade(sqLiteDatabase,i,i1);
        FoodIngredientContract.onUpgrade(sqLiteDatabase,i,i1);

    }
}

package com.afomic.bakingapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by afomic on 6/18/17.
 */

public class FoodStepContract {
    public static class FoodStepEntry implements BaseColumns {
        public static final String TABLE_NAME="food_step_table";
        public static final String COLUMN_STEP_ID="id";
        public static final String COLUMN_FOOD_ID="food_id";
        public static final String COLUMN_SHORT_DESCRIPTION="short_description";
        public static final String COLUMN_DESCRIPTION="description";
        public static final String COLUMN_VIDEO_URL="video_url";
        public static final String COLUMN_THUMBNAIL_URL="thumbnail_url";
        public static final String COLUMN_STEP_WATCH="watched";

    }
    public static void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ FoodStepEntry.TABLE_NAME+" ( ";
        sql+= FoodStepEntry.COLUMN_STEP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql+= FoodStepEntry.COLUMN_FOOD_ID+" INTEGER, ";
        sql+= FoodStepEntry.COLUMN_SHORT_DESCRIPTION+" VARCHAR(100), ";
        sql+= FoodStepEntry.COLUMN_DESCRIPTION+" VARCHAR(300), ";
        sql+= FoodStepEntry.COLUMN_VIDEO_URL+" VARCHAR(150), ";
        sql+= FoodStepEntry.COLUMN_STEP_WATCH+" INT(1), ";
        sql+= FoodStepEntry.COLUMN_THUMBNAIL_URL+" VARCHAR(100) ) ";
        db.execSQL(sql);

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable="DROP TABLE"+ FoodStepContract.FoodStepEntry.TABLE_NAME;
        db.execSQL(dropTable);
    }
}

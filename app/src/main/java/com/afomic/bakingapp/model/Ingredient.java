package com.afomic.bakingapp.model;

import android.database.Cursor;

import com.afomic.bakingapp.data.FoodIngredientContract;
import com.google.gson.annotations.SerializedName;

/**
 * Created by afomic on 6/15/17.
 */

public class Ingredient {
    @SerializedName("ingredient")
    private String mName;
    @SerializedName("measure")
    private String mMeasure;
    @SerializedName("quantity")
    private double mQuantity;
    private int mFoodID=0;

    public Ingredient(String mName, String mMeasure, double mQuantity) {
        this.mName = mName;
        this.mMeasure = mMeasure;
        this.mQuantity = mQuantity;
    }
    public Ingredient(Cursor cursor){
        mName=cursor.getString(cursor.getColumnIndexOrThrow(FoodIngredientContract.FoodIngredient.COLUMN_NAME));
        mMeasure=cursor.getString(cursor.getColumnIndexOrThrow(FoodIngredientContract.FoodIngredient.COLUMN_MEASURE));
        mQuantity=cursor.getDouble(cursor.getColumnIndexOrThrow(FoodIngredientContract.FoodIngredient.COLUMN_QUANTITY));
    }

    public String getName() {
        return mName;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public int getFoodID(){
        return mFoodID;
    }

    public String toString(){
        return String.format("%.1f %s of %s",mQuantity,mMeasure,mName);
    }
}

package com.afomic.bakingapp.model;

import android.database.Cursor;

import com.afomic.bakingapp.data.FoodContract;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 * Created by afomic on 6/15/17.
 */

public class Food {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String mName;
    @SerializedName("ingredients")
    private ArrayList<Ingredient> mIngredients;
    @SerializedName("steps")
    private ArrayList<RecipeStep> recipeSteps;

    public Food(int id,String mName, ArrayList<Ingredient> mIngredients,ArrayList<RecipeStep> steps) {
        this.mName = mName;
        this.id=id;
        this.recipeSteps =steps;
        this.mIngredients = mIngredients;
    }
    public Food(Cursor cursor){
        id =cursor.getInt(cursor.getColumnIndexOrThrow(FoodContract.FoodEntry.COLUMN_FOOD_ID));
        mName=cursor.getString(cursor.getColumnIndexOrThrow(FoodContract.FoodEntry.COLUMN_NAME));
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Ingredient> getIngredients() {
        return mIngredients;
    }
    public ArrayList<RecipeStep> getRecipeSteps(){
        return recipeSteps;
    }
    public int getID(){
        return id;
    }
}

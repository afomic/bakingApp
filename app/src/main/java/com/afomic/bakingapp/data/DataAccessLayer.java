package com.afomic.bakingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.afomic.bakingapp.model.Food;
import com.afomic.bakingapp.model.Ingredient;
import com.afomic.bakingapp.model.RecipeStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afomic on 6/18/17.
 *
 */

public class DataAccessLayer {
    private SQLiteDatabase db;
    private DatabaseOpenHelper helper;
    public DataAccessLayer(Context ctx){
        helper=new DatabaseOpenHelper(ctx);
    }
    public List<Food> getFood(){
        Cursor cu;
        try {
            db = helper.getReadableDatabase();
            cu = db.query(FoodContract.FoodEntry.TABLE_NAME,
                    null,
                    null,
                    null, null, null, null);
            List<Food> entries = new ArrayList<>();
            while (cu.moveToNext()) {
                Food mItem=new Food(cu);
                entries.add(mItem);

            }
            cu.close();
            return entries;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) db.close();
        }
    }

    public List<Ingredient> getIngredient(int foodID){
        Cursor cu;
        try {
            db = helper.getReadableDatabase();
            String[] whereArgs = {""+foodID};
            cu = db.query(FoodIngredientContract.FoodIngredient.TABLE_NAME,
                    null,
                    FoodIngredientContract.FoodIngredient.COLUMN_FOOD_ID+" =?",
                    whereArgs, null, null, null);
            List<Ingredient> entries=new ArrayList<>();
            while (cu.moveToNext()) {
                Ingredient mItem=new Ingredient(cu);
                entries.add(mItem);

            }
            cu.close();
            return entries;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) db.close();
        }
    }

    public List<RecipeStep> getSteps(int foodID){
        Cursor cu;
        try {
            db = helper.getReadableDatabase();
            String[] whereArgs = {""+foodID};
            cu = db.query(FoodStepContract.FoodStepEntry.TABLE_NAME,
                    null,
                    FoodStepContract.FoodStepEntry.COLUMN_FOOD_ID+" =?",
                    whereArgs, null, null, null);
            List<RecipeStep> entries = new ArrayList<>();
            while (cu.moveToNext()) {
                RecipeStep mItem=new RecipeStep(cu);
                entries.add(mItem);

            }
            cu.close();
            return entries;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) db.close();
        }
    }

    public boolean isDatabaseEmpty(){

        try{
            db=helper.getReadableDatabase();
            Cursor cursor=db.query(FoodContract.FoodEntry.TABLE_NAME,null,null,null,null,null,null);
            if(cursor.moveToFirst()) {
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }

    private ContentValues getContentValue(RecipeStep item, int foodID){
        ContentValues values =new ContentValues();
        values.put(FoodStepContract.FoodStepEntry.COLUMN_DESCRIPTION,item.getDesription());
        values.put(FoodStepContract.FoodStepEntry.COLUMN_THUMBNAIL_URL,item.getThumbnailUrl());
        values.put(FoodStepContract.FoodStepEntry.COLUMN_VIDEO_URL,item.getVideoUrl());
        values.put(FoodStepContract.FoodStepEntry.COLUMN_FOOD_ID,foodID);
        values.put(FoodStepContract.FoodStepEntry.COLUMN_SHORT_DESCRIPTION,item.getShortDescription());
        values.put(FoodStepContract.FoodStepEntry.COLUMN_STEP_WATCH,0);
        return values;
    }
    private ContentValues getContentValue(Food item){
        ContentValues values =new ContentValues();
        values.put(FoodContract.FoodEntry.COLUMN_FOOD_ID,item.getID());
        values.put(FoodContract.FoodEntry.COLUMN_NAME,item.getName());
        return values;
    }
    private ContentValues getContentValue(Ingredient item,int foodID) {
        ContentValues values = new ContentValues();
        values.put(FoodIngredientContract.FoodIngredient.COLUMN_FOOD_ID, foodID);
        values.put(FoodIngredientContract.FoodIngredient.COLUMN_MEASURE, item.getMeasure());
        values.put(FoodIngredientContract.FoodIngredient.COLUMN_QUANTITY, item.getQuantity());
        values.put(FoodIngredientContract.FoodIngredient.COLUMN_NAME, item.getName());
        values.put(FoodIngredientContract.FoodIngredient.COLUMN_NAME, item.getName());

        return values;
    }
    public void addFoods(List<Food> foods){
        db=helper.getWritableDatabase();
        try {
            db.beginTransaction();
            for(Food entry:foods){
                db.insert(FoodContract.FoodEntry.TABLE_NAME,null,getContentValue(entry));
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void addSteps(List<RecipeStep> recipeSteps, int foodID){
        db=helper.getWritableDatabase();
        try {
            db.beginTransaction();
            for(RecipeStep entry: recipeSteps){
                db.insert(FoodStepContract.FoodStepEntry.TABLE_NAME,null,getContentValue(entry,foodID));
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
    public void addIngredients(List<Ingredient> ingredients,int foodID){
        db=helper.getWritableDatabase();
        try {
            db.beginTransaction();
            for(Ingredient entry:ingredients){
                db.insert(FoodIngredientContract.FoodIngredient.TABLE_NAME,null,getContentValue(entry,foodID));
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
    public void setWatched(int stepID){

    }


}

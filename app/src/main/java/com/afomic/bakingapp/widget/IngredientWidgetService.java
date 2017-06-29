package com.afomic.bakingapp.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;


import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by afomic on 6/25/17.
 */

public class IngredientWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        DataAccessLayer dataSource=new DataAccessLayer(this.getApplicationContext());
        int foodID =intent.getIntExtra(Constant.BUNDLE_FOOD_ID,1);
        List<Ingredient> ingredients=dataSource.getIngredient(foodID);
        Log.d(Constant.TAG, "onGetViewFactory: "+ingredients.size());
        return new ingredientWidgetRemoteViewFactory(this.getApplicationContext(),ingredients);
    }
}

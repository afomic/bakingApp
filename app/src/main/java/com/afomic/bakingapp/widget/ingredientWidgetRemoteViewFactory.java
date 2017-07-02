package com.afomic.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.data.Constant;
import com.afomic.bakingapp.data.DataAccessLayer;
import com.afomic.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by afomic on 6/25/17.
 *
 */

public class ingredientWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private List<Ingredient> mIngredients;
    private Intent intent;
    private DataAccessLayer dataSource;
    private SharedPreferences sharedPreferences;

    public ingredientWidgetRemoteViewFactory(Context context){
        mContext = context;
        dataSource =new DataAccessLayer(mContext);
        sharedPreferences=context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }

    @Override
    public void onCreate() {
        int mFoodID= sharedPreferences.getInt(Constant.BUNDLE_FOOD_ID,1);
        mIngredients=dataSource.getIngredient(mFoodID);
    }

    @Override
    public void onDataSetChanged() {
        int mFoodID= sharedPreferences.getInt(Constant.BUNDLE_FOOD_ID,1);
        mIngredients=dataSource.getIngredient(mFoodID);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.e(Constant.TAG, "getViewAt: "+position );
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        Ingredient mItem=mIngredients.get(position);
        row.setTextViewText(R.id.tv_widget_ingredient_name,mItem.toString());
        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

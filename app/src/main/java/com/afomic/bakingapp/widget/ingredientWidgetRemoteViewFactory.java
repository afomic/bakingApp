package com.afomic.bakingapp.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.afomic.bakingapp.R;
import com.afomic.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by afomic on 6/25/17.
 *
 */

public class ingredientWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private List<Ingredient> mIngredients;

    public ingredientWidgetRemoteViewFactory(Context context,List<Ingredient> ingredients){
        mIngredients=ingredients;
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
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
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_item);
        Ingredient mItem=mIngredients.get(position);
        row.setTextViewText(R.id.tv_ingredient,mItem.toString());
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

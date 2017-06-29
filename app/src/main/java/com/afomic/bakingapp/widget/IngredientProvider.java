package com.afomic.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.afomic.bakingapp.R;
import com.afomic.bakingapp.data.Constant;


/**
 * Implementation of App Widget functionality.
 */
public class IngredientProvider extends AppWidgetProvider {
    private static String mFoodName;
    private static int mFoodID;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.tv_widget_food_name,mFoodName);

        Intent serviceIntent=new Intent(context,IngredientWidgetService.class);
        serviceIntent.putExtra(Constant.BUNDLE_FOOD_ID,mFoodID);
        views.setRemoteAdapter(R.id.widget_ingredient_list,serviceIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        Log.d(Constant.TAG, "updateAppWidget: first ");

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(Constant.TAG, "updateAppWidget: ");
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        mFoodID=intent.getIntExtra(Constant.BUNDLE_FOOD_ID,0);
        mFoodName=intent.getStringExtra(Constant.BUNDLE_FOOD_NAME);
    }
}


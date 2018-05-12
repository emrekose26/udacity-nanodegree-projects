package com.emrekose.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.emrekose.bakingapp.R;

public class RecipesAppWidgetProvider extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = getRecipesFromListRemoteView(context);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static RemoteViews getRecipesFromListRemoteView(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipes_app_widget);

        Intent intent = new Intent(context, RecipesWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.widget_ingredients_lv, intent);

        remoteViews.setEmptyView(R.id.widget_ingredients_lv, R.id.widget_empty_view);

        return remoteViews;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateAppWidgets(context, appWidgetManager, appWidgetIds);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }

    /*
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        ComponentName componentName = new ComponentName(context, RecipesAppWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        RemoteViews remoteViews = getRecipesFromListRemoteView(context);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_lv);
        onUpdate(context, appWidgetManager, appWidgetIds);

        /**
         *
         * AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
         RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_2x1);
         ComponentName thisWidget = new ComponentName(context, MyWidget.class);
         remoteViews.setTextViewText(R.id.my_text_view, "myText" + System.currentTimeMillis());
         appWidgetManager.updateAppWidget(thisWidget, remoteViews);
         }
        */

}

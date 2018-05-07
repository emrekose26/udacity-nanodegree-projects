package com.emrekose.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.ui.detail.RecipeDetailActivity;

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

        Intent detailIntent = new Intent(context, RecipeDetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_empty_view, pendingIntent);

        remoteViews.setEmptyView(R.id.widget_ingredients_lv, R.id.widget_empty_view);

        return remoteViews;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

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

}

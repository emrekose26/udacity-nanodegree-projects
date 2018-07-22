package com.emrekose.famula.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.emrekose.famula.R;
import com.emrekose.famula.ui.favorites.FavoritesActivity;

import java.util.Objects;

public class FamulaAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = getRestaurantsFromListView(context);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private static RemoteViews getRestaurantsFromListView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.famula_app_widget);

        Intent listViewIntent = new Intent(context, FamulaWidgetService.class);
        views.setRemoteAdapter(R.id.widget_restaurants_lv, listViewIntent);

        Intent favIntent = new Intent(context, FavoritesActivity.class);
        PendingIntent intent = PendingIntent.getActivity(context, 0, favIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_restaurants_lv, intent);

        views.setEmptyView(R.id.widget_restaurants_lv, R.id.widget_empty_view);

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        updateAppWidget(context, appWidgetManager, appWidgetId);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
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
        if (intent != null) {
            if (Objects.requireNonNull(intent.getAction()).equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context.getApplicationContext(), FamulaAppWidgetProvider.class);

                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                RemoteViews remoteViews = getRestaurantsFromListView(context);

                appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_restaurants_lv);
                onUpdate(context, appWidgetManager, appWidgetIds);
            }
        }
    }

}

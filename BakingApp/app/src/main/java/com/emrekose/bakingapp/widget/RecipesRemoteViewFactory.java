package com.emrekose.bakingapp.widget;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.data.local.BakingContract;
import com.emrekose.bakingapp.data.local.BakingProvider;

public class RecipesRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Cursor cursor;

    public RecipesRemoteViewFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) cursor.close();

        cursor = context.getContentResolver().query(
                BakingProvider.RecipeIngredients.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onDestroy() {
        if (cursor != null) cursor.close();
    }

    @Override
    public int getCount() {
        return (cursor != null) ? cursor.getCount() : 0;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if (cursor == null || cursor.getCount() == 0) return null;

        cursor.moveToPosition(i);
        int recipeNameIndex = cursor.getColumnIndex(BakingContract.COLUMN_RECIPE_NAME);
        int quantityMeasureIndex = cursor.getColumnIndex(BakingContract.COLUMN_QUANTITY_MEASURE);
        String recipeName = cursor.getString(recipeNameIndex);
        String quantityMeasure = cursor.getString(quantityMeasureIndex);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_item);
        // TODO: 7.05.2018 create span string for \n
        remoteViews.setTextViewText(R.id.widget_ingredients, recipeName);

        return remoteViews;
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
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

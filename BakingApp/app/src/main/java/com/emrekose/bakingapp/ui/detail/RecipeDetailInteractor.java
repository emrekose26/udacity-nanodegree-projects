package com.emrekose.bakingapp.ui.detail;

import android.content.ContentValues;
import android.content.Context;

import com.emrekose.bakingapp.data.local.BakingContract;
import com.emrekose.bakingapp.data.local.BakingProvider;

import javax.inject.Inject;

public class RecipeDetailInteractor {

    private Context context;

    @Inject
    public RecipeDetailInteractor(Context context) {
        this.context = context;
    }

    public void insertIngredient(String recipeName, String quantityMeasure) {
        ContentValues values = new ContentValues();

        values.put(BakingContract.COLUMN_RECIPE_NAME, recipeName);
        values.put(BakingContract.COLUMN_QUANTITY_MEASURE, quantityMeasure);

        context.getContentResolver().insert(BakingProvider.RecipeIngredients.CONTENT_URI, values);
    }
}

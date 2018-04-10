package com.emrekose.bakingapp.ui.recipes;

import com.emrekose.bakingapp.base.BaseCallback;
import com.emrekose.bakingapp.model.RecipeResponse;

import java.util.List;

public interface RecipesCallback extends BaseCallback {

    void onResponse(List<RecipeResponse> recipeList);

    void onRecipeError();
}

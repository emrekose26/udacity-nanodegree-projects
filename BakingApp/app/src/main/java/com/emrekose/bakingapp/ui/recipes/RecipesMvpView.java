package com.emrekose.bakingapp.ui.recipes;

import com.emrekose.bakingapp.base.MvpView;
import com.emrekose.bakingapp.model.RecipeResponse;

import java.util.List;

public interface RecipesMvpView extends MvpView {

    void renderRecipes(List<RecipeResponse> recipesList);
}

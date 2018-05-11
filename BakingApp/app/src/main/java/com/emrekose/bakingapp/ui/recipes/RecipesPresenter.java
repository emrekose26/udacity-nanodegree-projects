package com.emrekose.bakingapp.ui.recipes;

import com.emrekose.bakingapp.base.BasePresenter;
import com.emrekose.bakingapp.model.RecipeResponse;

import java.util.List;

import javax.inject.Inject;

public class RecipesPresenter implements BasePresenter<RecipesMvpView>, RecipesCallback {

    RecipesMvpView view;
    RecipesInteractor interactor;

    @Inject
    public RecipesPresenter(RecipesMvpView view, RecipesInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setView(RecipesMvpView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void getRecipes() {
        view.showLoading();
        interactor.loadRecipesFromApi(this);
    }

    public void handleScreeRotation(List<RecipeResponse> recipeList) {
        view.hideLoading();
        view.renderRecipes(recipeList);
    }

    @Override
    public void onResponse(List<RecipeResponse> recipeList) {
        view.hideLoading();
        view.renderRecipes(recipeList);
    }

    @Override
    public void onRecipeError() {
        view.showErrorMessage();
    }

    @Override
    public void onNetworkConnectionError() {
        view.showNetworkConnectionError();
    }

    @Override
    public void onServerError() {
        view.showServerError();
    }
}

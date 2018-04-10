package com.emrekose.bakingapp.ui.recipes;

import com.emrekose.bakingapp.data.remote.ApiSource;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.utils.HttpNotFound;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipesInteractor {

    ApiSource apiSource;

    @Inject
    public RecipesInteractor(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    public void loadRecipesFromApi(RecipesCallback callback) {
        apiSource.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> onSuccess(response, callback),
                        error -> onError(error, callback));
    }

    private void onSuccess(List<RecipeResponse> response, RecipesCallback callback) {
        if (response != null) {
            if (response.size() > 0) {
                callback.onResponse(response);
            } else {
                callback.onRecipeError();
            }
        } else {
            callback.onRecipeError();
        }
    }

    private void onError(Throwable t, RecipesCallback callback) {
        if (HttpNotFound.isHttp404(t)) {
            callback.onRecipeError();
        } else if (t.getMessage().equals(HttpNotFound.SERVER_ERROR)) {
            callback.onNetworkConnectionError();
        } else {
            callback.onServerError();
        }
    }
}

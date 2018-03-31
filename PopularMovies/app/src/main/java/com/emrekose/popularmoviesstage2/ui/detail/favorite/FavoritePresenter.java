package com.emrekose.popularmoviesstage2.ui.detail.favorite;

import com.emrekose.popularmoviesstage2.base.BasePresenter;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

import javax.inject.Inject;

public class FavoritePresenter implements BasePresenter<FavoriteMvpView>, FavoriteCallback  {

    FavoriteMvpView view;
    FavoriteInteractor interactor;

    @Inject
    public FavoritePresenter(FavoriteMvpView view, FavoriteInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setView(FavoriteMvpView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void addToFavorite(MovieResults results) {
        interactor.addMovieToFavorites(results);
        view.updateFavImage(true);
    }

    @Override
    public void removeToFavorite(MovieResults results) {
        interactor.removeMovieFromFavorites(results);
        view.updateFavImage(false);
    }

    @Override
    public boolean isMovieFavorited(long id) {
        return interactor.isFavorite(id);
    }
}

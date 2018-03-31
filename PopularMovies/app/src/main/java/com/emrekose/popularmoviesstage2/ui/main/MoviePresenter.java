package com.emrekose.popularmoviesstage2.ui.main;

import android.database.Cursor;

import com.emrekose.popularmoviesstage2.base.BasePresenter;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.util.SortType;

import java.util.List;

import javax.inject.Inject;


public class MoviePresenter implements BasePresenter<MovieMvpView>, MovieCallback {

    MovieMvpView view;
    MovieInteractor movieInteractor;

    @Inject
    public MoviePresenter(MovieMvpView view, MovieInteractor movieInteractor) {
        this.view = view;
        this.movieInteractor = movieInteractor;
    }

    @Override
    public void setView(MovieMvpView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void init(SortType sortType, Cursor cursor) {
        view.showLoading();
        switch (sortType) {
            case POPULAR:
                movieInteractor.loadPopularMoviesDataFromApi(this);
                break;
            case TOPRATED:
                movieInteractor.loadTopRatedMoviesDataFromApi(this);
                break;
            case FAVORITE:
                movieInteractor.getMoviesFromDb(cursor, this);
                break;
        }
    }

    public void handleScreenRotation(List<MovieResults> resultsList) {
        view.hideLoading();
        view.renderMovies(resultsList);
    }

    @Override
    public void onResponse(List<MovieResults> movieResults) {
        view.hideLoading();
        view.renderMovies(movieResults);
    }

    @Override
    public void onMovieError() {
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

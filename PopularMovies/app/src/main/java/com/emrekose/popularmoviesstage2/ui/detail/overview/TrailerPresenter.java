package com.emrekose.popularmoviesstage2.ui.detail.overview;

import com.emrekose.popularmoviesstage2.base.BasePresenter;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResults;

import java.util.List;

import javax.inject.Inject;


public class TrailerPresenter implements BasePresenter<TrailerMvpView>, TrailerCallback {

    TrailerMvpView view;
    TrailerInteractor trailerInteractor;

    @Inject
    public TrailerPresenter(TrailerMvpView view, TrailerInteractor trailerInteractor) {
        this.view = view;
        this.trailerInteractor = trailerInteractor;
    }

    @Override
    public void setView(TrailerMvpView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    public void init(int movieId) {
        view.showLoading();
        trailerInteractor.loadTrailersFromApi(movieId, this);
    }

    public void handleScreenRotation(List<MovieVideosResults> resultsList) {
        view.hideLoading();
        view.renderTrailers(resultsList);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onResponse(List<MovieVideosResults> movieVideosResults) {
        view.hideLoading();
        view.renderTrailers(movieVideosResults);
    }

    @Override
    public void onTrailerError() {
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

package com.emrekose.popularmoviesstage2.ui.detail.reviews;

import com.emrekose.popularmoviesstage2.base.BasePresenter;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResults;

import java.util.List;

import javax.inject.Inject;


public class ReviewPresenter implements BasePresenter<ReviewMvpView>, ReviewCallback {

    ReviewMvpView view;
    ReviewInteractor reviewInteractor;

    @Inject
    public ReviewPresenter(ReviewMvpView view, ReviewInteractor reviewInteractor) {
        this.view = view;
        this.reviewInteractor = reviewInteractor;
    }

    @Override
    public void setView(ReviewMvpView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    public void init(int movieId) {
        view.showLoading();
        reviewInteractor.loadReviewsFromApi(movieId, this);
    }

    public void handleScreenRotation(List<MovieReviewsResults> reviewsResultsList) {
        view.hideLoading();
        view.renderReviews(reviewsResultsList);
    }


    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onResponse(List<MovieReviewsResults> movieReviewsResults) {
        view.hideLoading();
        view.renderReviews(movieReviewsResults);
    }

    @Override
    public void onReviewError() {
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

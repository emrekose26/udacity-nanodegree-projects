package com.emrekose.popularmoviesstage2.ui.detail.reviews;

import com.emrekose.popularmoviesstage2.base.MvpView;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResults;

import java.util.List;

public interface ReviewMvpView extends MvpView {

    void renderReviews(List<MovieReviewsResults> movieReviewsResults);
}

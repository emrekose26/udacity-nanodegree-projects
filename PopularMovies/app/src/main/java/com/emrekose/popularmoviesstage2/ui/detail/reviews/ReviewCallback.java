package com.emrekose.popularmoviesstage2.ui.detail.reviews;

import com.emrekose.popularmoviesstage2.base.BaseCallback;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResults;

import java.util.List;


public interface ReviewCallback extends BaseCallback {

    void onResponse(List<MovieReviewsResults> movieReviewsResults);

    void onReviewError();
}

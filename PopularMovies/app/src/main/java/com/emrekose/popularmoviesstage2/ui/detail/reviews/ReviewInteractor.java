package com.emrekose.popularmoviesstage2.ui.detail.reviews;

import com.emrekose.popularmoviesstage2.data.remote.ApiSource;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResponse;
import com.emrekose.popularmoviesstage2.util.HttpNotFound;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ReviewInteractor {

    ApiSource apiSource;

    @Inject
    public ReviewInteractor(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    public void loadReviewsFromApi(int movieId, ReviewCallback callback) {
        apiSource.getMovieReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> onSuccess(response, callback),
                        error -> onError(error, callback));
    }

    private void onSuccess(MovieReviewsResponse response, ReviewCallback callback) {
        if (response.getResults() != null) {
            if (response.getResults().size() > 0) {
                callback.onResponse(response.getResults());
            } else {
                callback.onReviewError();
            }
        } else {
            callback.onReviewError();
        }
    }

    private void onError(Throwable t, ReviewCallback callback) {
        if (HttpNotFound.isHttp404(t)) {
            callback.onReviewError();
        } else if (t.getMessage().equals(HttpNotFound.SERVER_ERROR)) {
            callback.onNetworkConnectionError();
        } else {
            callback.onServerError();
        }
    }
}

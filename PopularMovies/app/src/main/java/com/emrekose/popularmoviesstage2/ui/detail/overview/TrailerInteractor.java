package com.emrekose.popularmoviesstage2.ui.detail.overview;

import com.emrekose.popularmoviesstage2.data.remote.ApiSource;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResponse;
import com.emrekose.popularmoviesstage2.util.HttpNotFound;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TrailerInteractor {

    ApiSource apiSource;

    @Inject
    public TrailerInteractor(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    public void loadTrailersFromApi(int movieId, TrailerCallback callback) {
        apiSource.getMovieTrailer(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> onSuccess(response, callback),
                        error -> onError(error, callback));
    }

    private void onSuccess(MovieVideosResponse response, TrailerCallback callback) {
        if (response.getResults() != null) {
            if (response.getResults().size() > 0) {
                callback.onResponse(response.getResults());
            } else {
                callback.onTrailerError();
            }
        } else {
            callback.onTrailerError();
        }
    }

    private void onError(Throwable t, TrailerCallback callback) {
        if (HttpNotFound.isHttp404(t)) {
            callback.onTrailerError();
        } else if (t.getMessage().equals(HttpNotFound.SERVER_ERROR)) {
            callback.onNetworkConnectionError();
        } else {
            callback.onServerError();
        }
    }
}

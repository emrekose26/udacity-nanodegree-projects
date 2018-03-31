package com.emrekose.popularmoviesstage2.ui.main;

import android.database.Cursor;

import com.emrekose.popularmoviesstage2.data.local.MoviesContract;
import com.emrekose.popularmoviesstage2.data.remote.ApiSource;
import com.emrekose.popularmoviesstage2.model.movie.MovieResponse;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.util.HttpNotFound;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MovieInteractor {

    ApiSource apiSource;

    @Inject
    public MovieInteractor(ApiSource apiSource) {
        this.apiSource = apiSource;
    }

    public void loadPopularMoviesDataFromApi(MovieCallback movieCallback) {
        apiSource.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> onSuccess(response, movieCallback),
                        error -> onError(error, movieCallback));
    }

    public void loadTopRatedMoviesDataFromApi(MovieCallback movieCallback) {
        apiSource.getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> onSuccess(response, movieCallback),
                        error -> onError(error, movieCallback));
    }

    public void getMoviesFromDb(Cursor cursor, MovieCallback callback) {
        List<MovieResults> resultsList = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int movieId = cursor.getInt(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_TITLE));
                    String overview = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_OVERVIEW));
                    Double voteAvr = cursor.getDouble(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE));
                    String releaseDate = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE));
                    String poster = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH));
                    String backDrop = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_BACKDROP_PATH));

                    MovieResults results = new MovieResults();
                    results.setId(movieId);
                    results.setTitle(title);
                    results.setOverview(overview);
                    results.setVote_average(voteAvr);
                    results.setRelease_date(releaseDate);
                    results.setPoster_path(poster);
                    results.setBackdrop_path(backDrop);

                    resultsList.add(results);

                } while (cursor.moveToNext());
            }
        }

        callback.onResponse(resultsList);
    }


    public void onSuccess(MovieResponse response, MovieCallback movieCallback) {
        if (response.getResults() != null) {
            if (response.getResults().size() > 0) {
                movieCallback.onResponse(response.getResults());
            } else {
                movieCallback.onMovieError();
            }
        } else {
            movieCallback.onMovieError();
        }
    }

    private void onError(Throwable t, MovieCallback movieCallback) {
        if (HttpNotFound.isHttp404(t)) {
            movieCallback.onMovieError();
        } else if (t.getMessage().equals(HttpNotFound.SERVER_ERROR)) {
            movieCallback.onNetworkConnectionError();
        } else {
            movieCallback.onServerError();
        }
    }
}

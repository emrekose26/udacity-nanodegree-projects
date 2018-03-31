package com.emrekose.popularmoviesstage2.ui.main;

import com.emrekose.popularmoviesstage2.base.BaseCallback;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

import java.util.List;

public interface MovieCallback extends BaseCallback {

    void onResponse(List<MovieResults> movieResults);

    void onMovieError();
}

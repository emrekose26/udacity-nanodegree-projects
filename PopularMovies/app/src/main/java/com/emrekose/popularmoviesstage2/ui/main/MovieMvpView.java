package com.emrekose.popularmoviesstage2.ui.main;

import com.emrekose.popularmoviesstage2.base.MvpView;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

import java.util.List;

/**
 * Created by emrekose on 11.03.2018.
 */

public interface MovieMvpView extends MvpView {

    void renderMovies(List<MovieResults> movieResults);
}

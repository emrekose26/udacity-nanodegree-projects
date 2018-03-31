package com.emrekose.popularmoviesstage2.ui.detail.overview;

import com.emrekose.popularmoviesstage2.base.MvpView;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResults;

import java.util.List;


public interface TrailerMvpView extends MvpView {

    void renderTrailers(List<MovieVideosResults> movieVideosResultsList);
}

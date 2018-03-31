package com.emrekose.popularmoviesstage2.ui.detail.overview;

import com.emrekose.popularmoviesstage2.base.BaseCallback;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResults;

import java.util.List;


public interface TrailerCallback extends BaseCallback {

    void onResponse(List<MovieVideosResults> movieVideosResults);

    void onTrailerError();
}

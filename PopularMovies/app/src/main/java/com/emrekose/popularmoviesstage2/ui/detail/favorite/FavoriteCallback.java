package com.emrekose.popularmoviesstage2.ui.detail.favorite;

import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

public interface FavoriteCallback {

    void addToFavorite(MovieResults results);

    void removeToFavorite(MovieResults results);

    boolean isMovieFavorited(long id);
}

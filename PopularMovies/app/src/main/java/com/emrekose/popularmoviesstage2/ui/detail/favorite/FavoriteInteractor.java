package com.emrekose.popularmoviesstage2.ui.detail.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.emrekose.popularmoviesstage2.data.local.MoviesContract;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

import javax.inject.Inject;

public class FavoriteInteractor {

    private Context context;

    @Inject
    public FavoriteInteractor(Context context) {
        this.context = context;
    }

    public void addMovieToFavorites(MovieResults movieResults) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MoviesEntry.COLUMN_ID, movieResults.getId());
        values.put(MoviesContract.MoviesEntry.COLUMN_TITLE, movieResults.getTitle());
        values.put(MoviesContract.MoviesEntry.COLUMN_OVERVIEW, movieResults.getOverview());
        values.put(MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE, movieResults.getVote_average());
        values.put(MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE, movieResults.getRelease_date());
        values.put(MoviesContract.MoviesEntry.COLUMN_POSTER_PATH, movieResults.getPoster_path());
        values.put(MoviesContract.MoviesEntry.COLUMN_BACKDROP_PATH, movieResults.getBackdrop_path());

        context.getContentResolver().insert(MoviesContract.MoviesEntry.CONTENT_URI, values);
    }

    public void removeMovieFromFavorites(MovieResults movieResults) {
        Uri uri = MoviesContract.MoviesEntry.CONTENT_URI.buildUpon().appendPath(String.valueOf(movieResults.getId())).build();
        context.getContentResolver().delete(
                uri,
                null,
                null
        );
    }

    public boolean isFavorite(long id) {
        boolean favorite = false;

        Cursor cursor = context.getContentResolver().query(
                MoviesContract.MoviesEntry.CONTENT_URI,
                null,
                "id=?",
                new String[]{String.valueOf(id)},
                null
        );
        if (cursor != null) {
            favorite = cursor.getCount() != 0;
            cursor.close();
        }
        return favorite;
    }
}

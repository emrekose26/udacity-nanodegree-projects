package com.emrekose.popularmoviesstage2.data.local;

import android.net.Uri;
import android.provider.BaseColumns;


public class MoviesContract {
    public static final String CONTENT_AUTHORITY = "com.emrekose.popularmoviesstage2";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final class MoviesEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES)
                .build();

        // tables
        public static final String TABLE_NAME = "movies";

        // columns
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_POSTER_PATH = "movie_poster_path";
        public static final String COLUMN_OVERVIEW = "movie_overview";
        public static final String COLUMN_RELEASE_DATE = "movie_release_date";
        public static final String COLUMN_TITLE = "movie_title";
        public static final String COLUMN_VOTE_AVERAGE = "movie_vote_average";
        public static final String COLUMN_BACKDROP_PATH = "movie_backdrop";
    }
}

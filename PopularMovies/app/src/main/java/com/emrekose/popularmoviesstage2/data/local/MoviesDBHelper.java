package com.emrekose.popularmoviesstage2.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MoviesDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";

    public static final int DATABASE_VERSION = 1;

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE_SQL =
                "CREATE TABLE IF NOT EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME + " ("
                        + MoviesContract.MoviesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + MoviesContract.MoviesEntry.COLUMN_TITLE + " TEXT,"
                        + MoviesContract.MoviesEntry.COLUMN_OVERVIEW + " TEXT,"
                        + MoviesContract.MoviesEntry.COLUMN_VOTE_AVERAGE + " TEXT,"
                        + MoviesContract.MoviesEntry.COLUMN_RELEASE_DATE + " TEXT,"
                        + MoviesContract.MoviesEntry.COLUMN_BACKDROP_PATH + " TEXT,"
                        + MoviesContract.MoviesEntry.COLUMN_POSTER_PATH + " TEXT );";


        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

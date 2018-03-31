package com.emrekose.popularmoviesstage2.ui.main;

import android.support.v7.util.DiffUtil;

import com.emrekose.popularmoviesstage2.model.movie.MovieResults;

import java.util.List;


public class MovieDiffCallback extends DiffUtil.Callback {

    List<MovieResults> oldMovieList;
    List<MovieResults> newMovieList;

    public MovieDiffCallback(List<MovieResults> oldMovieList, List<MovieResults> newMovieList) {
        this.oldMovieList = oldMovieList;
        this.newMovieList = newMovieList;
    }

    @Override
    public int getOldListSize() {
        return oldMovieList.size();
    }

    @Override
    public int getNewListSize() {
        return newMovieList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieList.get(oldItemPosition).getId() == newMovieList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovieList.get(oldItemPosition).equals(newMovieList.get(newItemPosition));
    }
}

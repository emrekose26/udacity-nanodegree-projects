package com.emrekose.popularmoviesstage2.model.videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieVideosResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<MovieVideosResults> results;

    public int getId() {
        return id;
    }

    public List<MovieVideosResults> getResults() {
        return results;
    }

}

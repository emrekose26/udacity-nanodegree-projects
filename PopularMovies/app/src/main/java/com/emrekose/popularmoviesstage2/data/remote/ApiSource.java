package com.emrekose.popularmoviesstage2.data.remote;

import com.emrekose.popularmoviesstage2.model.detail.MovieDetailsResponse;
import com.emrekose.popularmoviesstage2.model.movie.MovieResponse;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResponse;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiSource {
    // Popular
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies();

    // Top Rated
    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies();

    // Details
    @GET("movie/{id}")
    Observable<MovieDetailsResponse> getMovieDetails(@Path("id") int movieID);

    // Trailers
    @GET("movie/{id}/videos")
    Observable<MovieVideosResponse> getMovieTrailer(@Path("id") int movieID);

    // Reviews
    @GET("movie/{id}/reviews")
    Observable<MovieReviewsResponse> getMovieReviews(@Path("id") int movieID);
}

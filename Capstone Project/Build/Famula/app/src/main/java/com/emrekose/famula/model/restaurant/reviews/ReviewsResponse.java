package com.emrekose.famula.model.restaurant.reviews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {

    @SerializedName("reviews_count")
    private int reviewsCount;

    @SerializedName("reviews_start")
    private int reviewsStart;

    @SerializedName("reviews_shown")
    private int reviewsShown;

    @SerializedName("user_reviews")
    private List<UserReview> userReviews = null;


    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public int getReviewsStart() {
        return reviewsStart;
    }

    public void setReviewsStart(int reviewsStart) {
        this.reviewsStart = reviewsStart;
    }

    public int getReviewsShown() {
        return reviewsShown;
    }

    public void setReviewsShown(int reviewsShown) {
        this.reviewsShown = reviewsShown;
    }

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}

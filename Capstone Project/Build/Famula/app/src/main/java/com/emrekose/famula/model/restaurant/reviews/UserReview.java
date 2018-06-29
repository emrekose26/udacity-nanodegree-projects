package com.emrekose.famula.model.restaurant.reviews;

import com.google.gson.annotations.SerializedName;

public class UserReview {

    @SerializedName("review")
    private Review review;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}

package com.emrekose.famula.model.restaurant.detail;

import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    private String aggregateRating;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("votes")
    private String votes;


    public String getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}

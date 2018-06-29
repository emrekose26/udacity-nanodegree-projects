package com.emrekose.famula.model.restaurant.reviews;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("rating")
    private int rating;

    @SerializedName("review_text")
    private String reviewText;

    @SerializedName("id")
    private String id;

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("review_time_friendly")
    private String reviewTimeFriendly;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("timestamp")
    private int timestamp;

    @SerializedName("likes")
    private int likes;

    @SerializedName("user")
    private User user;

    @SerializedName("comments_count")
    private int commentsCount;


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    public void setReviewTimeFriendly(String reviewTimeFriendly) {
        this.reviewTimeFriendly = reviewTimeFriendly;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}

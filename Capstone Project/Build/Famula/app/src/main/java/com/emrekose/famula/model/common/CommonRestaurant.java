package com.emrekose.famula.model.common;

import java.io.Serializable;

public class CommonRestaurant implements Serializable{

    private String name;
    private String url;
    private String address;
    private String locality;
    private String latitude;
    private String longitude;
    private String cuisines;
    private int averageCostForTwo;
    private int priceRange;
    private String currency;
    private String thumb;
    private String aggregateRating;
    private String ratingColor;
    private String featuredImage;
    private int hasOnlineDelivery;
    private int isDeliveringNow;
    private int hasTableBooking;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(int averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public int getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(int hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public int getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(int isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public int getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(int hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }
}

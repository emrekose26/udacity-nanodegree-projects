package com.emrekose.famula.model.restaurant.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable{

    @SerializedName("restaurant")
    private RestaurantItem restaurant;

    public RestaurantItem getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantItem restaurant) {
        this.restaurant = restaurant;
    }

}

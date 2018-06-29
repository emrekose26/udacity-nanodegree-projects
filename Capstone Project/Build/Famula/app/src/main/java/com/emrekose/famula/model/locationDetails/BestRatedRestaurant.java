package com.emrekose.famula.model.locationDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BestRatedRestaurant {

    @SerializedName("restaurant")
    private List<Restaurant> restaurantList;

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}

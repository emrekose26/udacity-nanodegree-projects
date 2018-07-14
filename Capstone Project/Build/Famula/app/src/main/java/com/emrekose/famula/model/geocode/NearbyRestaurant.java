package com.emrekose.famula.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NearbyRestaurant implements Serializable {

    @SerializedName("restaurant")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

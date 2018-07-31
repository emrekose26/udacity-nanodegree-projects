package com.emrekose.famula.model.cuisines;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cuisine implements Serializable {

    @SerializedName("cuisine")
    private CuisineItem cuisine;

    public CuisineItem getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineItem cuisine) {
        this.cuisine = cuisine;
    }
}

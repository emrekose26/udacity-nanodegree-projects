package com.emrekose.famula.model.cuisines;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CuisineItem implements Serializable{

    @SerializedName("cuisine_id")
    private int cuisineId;

    @SerializedName("cuisine_name")
    private String cuisineName;

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }
}

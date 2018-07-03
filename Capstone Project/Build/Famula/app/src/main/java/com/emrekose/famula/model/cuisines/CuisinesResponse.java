package com.emrekose.famula.model.cuisines;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisinesResponse {

    @SerializedName("cuisines")
    private List<Cuisine> cuisines;

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }
}

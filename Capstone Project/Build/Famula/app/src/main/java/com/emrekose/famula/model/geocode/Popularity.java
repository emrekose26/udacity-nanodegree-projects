package com.emrekose.famula.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Popularity {

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("nightlife_index")
    private String nightlifeIndex;

    @SerializedName("nearby_res")
    private List<String> nearbyRes = null;

    @SerializedName("top_cuisines")
    private List<String> topCuisines = null;

    @SerializedName("popularity_res")
    private String popularityRes;

    @SerializedName("nightlife_res")
    private String nightlifeRes;

    @SerializedName("subzone")
    private String subzone;

    @SerializedName("subzone_id")
    private int subzoneId;

    @SerializedName("city")
    private String city;


    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getNightlifeIndex() {
        return nightlifeIndex;
    }

    public void setNightlifeIndex(String nightlifeIndex) {
        this.nightlifeIndex = nightlifeIndex;
    }

    public List<String> getNearbyRes() {
        return nearbyRes;
    }

    public void setNearbyRes(List<String> nearbyRes) {
        this.nearbyRes = nearbyRes;
    }

    public List<String> getTopCuisines() {
        return topCuisines;
    }

    public void setTopCuisines(List<String> topCuisines) {
        this.topCuisines = topCuisines;
    }

    public String getPopularityRes() {
        return popularityRes;
    }

    public void setPopularityRes(String popularityRes) {
        this.popularityRes = popularityRes;
    }

    public String getNightlifeRes() {
        return nightlifeRes;
    }

    public void setNightlifeRes(String nightlifeRes) {
        this.nightlifeRes = nightlifeRes;
    }

    public String getSubzone() {
        return subzone;
    }

    public void setSubzone(String subzone) {
        this.subzone = subzone;
    }

    public int getSubzoneId() {
        return subzoneId;
    }

    public void setSubzoneId(int subzoneId) {
        this.subzoneId = subzoneId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

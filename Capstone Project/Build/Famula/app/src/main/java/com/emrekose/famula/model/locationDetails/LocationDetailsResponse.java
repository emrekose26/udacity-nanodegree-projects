package com.emrekose.famula.model.locationDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationDetailsResponse {

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("nightlife_index")
    private String nightlifeIndex;

    @SerializedName("nearby_res")
    private List<String> nearbyRes;

    @SerializedName("top_cuisines")
    private List<String> topCuisines;

    @SerializedName("popularity_res")
    private String popularityRes;

    @SerializedName("nightlife_res")
    private String nightlifeRes;

    @SerializedName("subzone")
    private String subzone;

    @SerializedName("subzone_id")
    private Integer subzoneId;

    @SerializedName("city")
    private String city;

    @SerializedName("location")
    private Location location;

    @SerializedName("num_restaurant")
    private Integer numRestaurant;

    @SerializedName("best_rated_restaurant")
    private List<BestRatedRestaurant> bestRatedRestaurant = null;


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

    public Integer getSubzoneId() {
        return subzoneId;
    }

    public void setSubzoneId(Integer subzoneId) {
        this.subzoneId = subzoneId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getNumRestaurant() {
        return numRestaurant;
    }

    public void setNumRestaurant(Integer numRestaurant) {
        this.numRestaurant = numRestaurant;
    }

    public List<BestRatedRestaurant> getBestRatedRestaurant() {
        return bestRatedRestaurant;
    }

    public void setBestRatedRestaurant(List<BestRatedRestaurant> bestRatedRestaurant) {
        this.bestRatedRestaurant = bestRatedRestaurant;
    }
}

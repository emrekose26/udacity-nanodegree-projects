package com.emrekose.famula.model.locations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationsResponse {

    @SerializedName("location_suggestions")
    private List<LocationSuggestion> locationSuggestions = null;

    @SerializedName("status")
    private String status;

    @SerializedName("has_more")
    private int hasMore;

    @SerializedName("has_total")
    private int hasTotal;


    public List<LocationSuggestion> getLocationSuggestions() {
        return locationSuggestions;
    }

    public void setLocationSuggestions(List<LocationSuggestion> locationSuggestions) {
        this.locationSuggestions = locationSuggestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public int getHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(int hasTotal) {
        this.hasTotal = hasTotal;
    }

}

package com.emrekose.famula.model.geocode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GeocodeResponse implements Serializable {

    @SerializedName("location")
    private Location location;

    @SerializedName("popularity")
    private Popularity popularity;

    @SerializedName("link")
    private String link;

    @SerializedName("nearby_restaurants")
    private List<NearbyRestaurant> nearbyRestaurants;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<NearbyRestaurant> getNearbyRestaurants() {
        return nearbyRestaurants;
    }

    public void setNearbyRestaurants(List<NearbyRestaurant> nearbyRestaurants) {
        this.nearbyRestaurants = nearbyRestaurants;
    }

}

package com.emrekose.famula.model.restaurant.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable{

    @SerializedName("address")
    private String address;

    @SerializedName("locality")
    private String locality;

    @SerializedName("city")
    private String city;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("country_id")
    private int countryId;

    @SerializedName("locality_verbose")
    private String localityVerbose;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }

    public void setLocalityVerbose(String localityVerbose) {
        this.localityVerbose = localityVerbose;
    }
}

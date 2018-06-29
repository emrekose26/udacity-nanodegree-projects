package com.emrekose.famula.model.restaurant.reviews;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("foodie_level")
    private String foodieLevel;

    @SerializedName("foodie_level_num")
    private int foodieLevelNum;

    @SerializedName("foodie_color")
    private String foodieColor;

    @SerializedName("profile_url")
    private String profileUrl;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("profile_deeplink")
    private String profileDeeplink;

    @SerializedName("zomato_handle")
    private String zomatoHandle;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodieLevel() {
        return foodieLevel;
    }

    public void setFoodieLevel(String foodieLevel) {
        this.foodieLevel = foodieLevel;
    }

    public int getFoodieLevelNum() {
        return foodieLevelNum;
    }

    public void setFoodieLevelNum(int foodieLevelNum) {
        this.foodieLevelNum = foodieLevelNum;
    }

    public String getFoodieColor() {
        return foodieColor;
    }

    public void setFoodieColor(String foodieColor) {
        this.foodieColor = foodieColor;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    public void setProfileDeeplink(String profileDeeplink) {
        this.profileDeeplink = profileDeeplink;
    }

    public String getZomatoHandle() {
        return zomatoHandle;
    }

    public void setZomatoHandle(String zomatoHandle) {
        this.zomatoHandle = zomatoHandle;
    }
}

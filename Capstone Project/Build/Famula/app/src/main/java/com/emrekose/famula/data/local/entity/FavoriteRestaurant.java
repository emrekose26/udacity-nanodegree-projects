package com.emrekose.famula.data.local.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favrestaurant")
public class FavoriteRestaurant {

    @NonNull
    @PrimaryKey
    public String id;

    public String name;

    @Embedded
    public FavLocationItem location;

    public int switchToOrderMenu;

    public String cuisines;

    public int averageCostForTwo;

    public int priceRange;

    public String currency;

    public int opentableSupport;

    public String thumb;

    public String featuredImage;

    @Embedded
    public FavUserRating userRating;

    public int hasOnlineDelivery;

    public int isDeliveringNow;

    public int hasTableBooking;
}

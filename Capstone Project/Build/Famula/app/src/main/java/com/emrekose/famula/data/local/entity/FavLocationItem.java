package com.emrekose.famula.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavLocationItem {

    @PrimaryKey(autoGenerate = true)
    public int locationItemId;

    public int cityId;

    public String address;

    public String locality;

    public String city;

    public double lat;

    public double lon;

    public int countyId;
}

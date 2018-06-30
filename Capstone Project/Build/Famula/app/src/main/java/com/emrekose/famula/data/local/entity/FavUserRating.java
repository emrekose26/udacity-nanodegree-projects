package com.emrekose.famula.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavUserRating {

    @PrimaryKey(autoGenerate = true)
    public int userRatingId;

    public String aggregateRating;

    public String ratingText;

    public String ratingColor;

    public String votes;
}

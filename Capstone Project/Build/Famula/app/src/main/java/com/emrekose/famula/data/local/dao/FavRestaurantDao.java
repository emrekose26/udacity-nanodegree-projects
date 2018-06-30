package com.emrekose.famula.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.emrekose.famula.data.local.entity.FavoriteRestaurant;

import java.util.List;

@Dao
public interface FavRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertFavorite(List<FavoriteRestaurant> favoriteRestaurantList);

    @Query("SELECT * FROM favrestaurant")
    abstract LiveData<FavoriteRestaurant> getAllFavorites();


}

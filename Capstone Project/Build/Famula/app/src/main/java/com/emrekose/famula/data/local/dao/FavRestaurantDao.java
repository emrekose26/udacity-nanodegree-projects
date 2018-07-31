package com.emrekose.famula.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.emrekose.famula.data.local.entity.CommonRestaurant;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertFavorite(CommonRestaurant restaurant);

    @Delete
    abstract void deleteFavRestaurant(CommonRestaurant commonRestaurant);

    @Query("SELECT * FROM favrestaurant")
    abstract LiveData<List<CommonRestaurant>> getAllFavorites();

    @Query("SELECT * FROM favrestaurant")
    abstract List<CommonRestaurant> getAllFavsForWidget();

    @Query("SELECT * FROM favrestaurant WHERE id = :id")
    abstract Flowable<CommonRestaurant> getSingleRestaurant(String id);
}

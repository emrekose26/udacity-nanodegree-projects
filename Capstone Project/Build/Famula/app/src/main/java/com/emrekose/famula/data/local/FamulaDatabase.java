package com.emrekose.famula.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.emrekose.famula.data.local.dao.FavRestaurantDao;
import com.emrekose.famula.data.local.entity.CommonRestaurant;

@Database(entities = {CommonRestaurant.class}, version = 1, exportSchema = false)
public abstract class FamulaDatabase extends RoomDatabase {

    public abstract FavRestaurantDao favRestaurantDao();
}

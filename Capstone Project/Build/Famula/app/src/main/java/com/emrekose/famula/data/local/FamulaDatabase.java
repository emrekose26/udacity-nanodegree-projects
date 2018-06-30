package com.emrekose.famula.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.emrekose.famula.data.local.dao.FavRestaurantDao;
import com.emrekose.famula.data.local.entity.FavLocationItem;
import com.emrekose.famula.data.local.entity.FavUserRating;
import com.emrekose.famula.data.local.entity.FavoriteRestaurant;

@Database(entities = {FavoriteRestaurant.class, FavUserRating.class, FavLocationItem.class} , version = 1, exportSchema = false)
abstract class FamulaDatabase extends RoomDatabase{

    public abstract FavRestaurantDao favRestaurantDao();
}

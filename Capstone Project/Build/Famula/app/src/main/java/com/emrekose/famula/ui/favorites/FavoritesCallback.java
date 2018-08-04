package com.emrekose.famula.ui.favorites;

import android.widget.ImageView;

import com.emrekose.famula.data.local.entity.CommonRestaurant;

public interface FavoritesCallback {
    void onFavoriteRestaurantClick(CommonRestaurant restaurant, ImageView sharedElement);

    void onFavoriteRestaurantMarkerClick(CommonRestaurant restaurant);
}

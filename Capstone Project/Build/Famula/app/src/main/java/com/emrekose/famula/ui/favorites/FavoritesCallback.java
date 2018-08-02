package com.emrekose.famula.ui.favorites;

import com.emrekose.famula.data.local.entity.CommonRestaurant;

public interface FavoritesCallback {
    void onFavoriteRestaurantClick(CommonRestaurant restaurant);

    void onFavoriteRestaurantMarkerClick(CommonRestaurant restaurant);
}

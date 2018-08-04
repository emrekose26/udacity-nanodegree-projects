package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.widget.ImageView;

import com.emrekose.famula.model.restaurant.search.Restaurant;

public interface CuisinesRestauCallback {
    void onRestaurantClick(Restaurant restaurant, ImageView sharedElement);

    void onRestaurantMarkerClick(Restaurant restaurant);
}

package com.emrekose.famula.ui.nearbyrestaurants;

import android.widget.ImageView;

import com.emrekose.famula.model.geocode.NearbyRestaurant;

public interface NearbyRestaurantsCallback {
    void onNearbyRestaurantClick(NearbyRestaurant restaurant, ImageView sharedElement);

    void onNearbyRestaurantMarkerClick(NearbyRestaurant restaurant);
}

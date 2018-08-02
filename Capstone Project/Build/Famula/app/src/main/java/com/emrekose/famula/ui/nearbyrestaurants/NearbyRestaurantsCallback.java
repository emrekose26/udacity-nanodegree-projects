package com.emrekose.famula.ui.nearbyrestaurants;

import com.emrekose.famula.model.geocode.NearbyRestaurant;

public interface NearbyRestaurantsCallback {
    void onNearbyRestaurantClick(NearbyRestaurant restaurant);

    void onNearbyRestaurantMarkerClick(NearbyRestaurant restaurant);
}

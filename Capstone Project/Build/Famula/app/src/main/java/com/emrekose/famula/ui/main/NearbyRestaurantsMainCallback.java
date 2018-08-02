package com.emrekose.famula.ui.main;

import com.emrekose.famula.model.geocode.NearbyRestaurant;

public interface NearbyRestaurantsMainCallback {

    void onMainNearbyRestaurantsClick(NearbyRestaurant nearbyRestaurant);

    void onMainNearbyRestaurantMarkerClick(NearbyRestaurant nearbyRestaurant);
}

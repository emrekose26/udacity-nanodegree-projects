package com.emrekose.famula.ui.main;

import android.widget.ImageView;

import com.emrekose.famula.model.geocode.NearbyRestaurant;

public interface NearbyRestaurantsMainCallback {

    void onMainNearbyRestaurantsClick(NearbyRestaurant nearbyRestaurant, ImageView sharedElement);

    void onMainNearbyRestaurantMarkerClick(NearbyRestaurant nearbyRestaurant);
}

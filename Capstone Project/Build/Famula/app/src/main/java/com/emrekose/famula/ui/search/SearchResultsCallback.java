package com.emrekose.famula.ui.search;

import android.widget.ImageView;

import com.emrekose.famula.model.restaurant.search.Restaurant;

public interface SearchResultsCallback {
    void onRestaurantClick(Restaurant restaurant, ImageView sharedElement);

    void onRestaurantMarkerClick(Restaurant restaurant);
}

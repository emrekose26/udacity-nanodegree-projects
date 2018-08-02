package com.emrekose.famula.ui.search;

import com.emrekose.famula.model.restaurant.search.Restaurant;

public interface SearchResultsCallback {
    void onRestaurantClick(Restaurant restaurant);

    void onRestaurantMarkerClick(Restaurant restaurant);
}

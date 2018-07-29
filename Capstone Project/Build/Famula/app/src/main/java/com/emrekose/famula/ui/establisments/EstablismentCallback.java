package com.emrekose.famula.ui.establisments;

import com.emrekose.famula.model.establisments.Establishment;
import com.emrekose.famula.model.restaurant.search.Restaurant;

public interface EstablismentCallback {

    interface TypesCalback {
        void onEstablismentTypesClick(Establishment establishment);
    }

    interface RestaurantCallback {
        void onEstablismentClick(Restaurant restaurant);
    }
}

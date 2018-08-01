package com.emrekose.famula.di;

import com.emrekose.famula.ui.cuisineslist.CuisinesListActivity;
import com.emrekose.famula.ui.cuisineslist.restaurants.CuisinesRestauActivity;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.ui.establisments.EstablismentsActivity;
import com.emrekose.famula.ui.favorites.FavoritesActivity;
import com.emrekose.famula.ui.main.MainActivity;
import com.emrekose.famula.ui.nearbyrestaurants.NearbyRestaurantsActivity;
import com.emrekose.famula.ui.search.SearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract CuisinesListActivity cuisinesListActivity();

    @ContributesAndroidInjector
    abstract NearbyRestaurantsActivity nearbyRestaurantsActivity();

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract EstablismentsActivity establismentsActivity();

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract RestaurantDetailActivity restaurantDetailActivity();

    @ContributesAndroidInjector
    abstract FavoritesActivity favoritesActivity();

    @ContributesAndroidInjector
    abstract CuisinesRestauActivity cuisinesRestauActivity();

    @ContributesAndroidInjector
    abstract SearchActivity searchActivity();
}

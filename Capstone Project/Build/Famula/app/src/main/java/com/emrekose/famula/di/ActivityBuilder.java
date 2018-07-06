package com.emrekose.famula.di;

import com.emrekose.famula.ui.cuisineslist.CuisinesListActivity;
import com.emrekose.famula.ui.establisments.EstablismentsActivity;
import com.emrekose.famula.ui.main.MainActivity;
import com.emrekose.famula.ui.nearbyrestaurants.NearbyRestaurantsActivity;

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
}

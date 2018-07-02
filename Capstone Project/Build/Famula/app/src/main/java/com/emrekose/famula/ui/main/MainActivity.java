package com.emrekose.famula.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityMainBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.geocode.NearbyRestaurant;

public class MainActivity extends BaseOnlyActivity<ActivityMainBinding, MainViewModel> implements CuisinesCallback, NearbyRestaurantsCallback {

    private static final int TAKEN_CUISINES = 10;
    private static final int TAKEN_NEARBY_RESTAURANTS = 5;

    private CuisinesRecyclerAdapter cuisinesAdapter;
    private NearbyRestaurantRecylerAdapter nearbyAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding.setLifecycleOwner(this);

        cuisinesAdapter = new CuisinesRecyclerAdapter(new CuisinesRecyclerAdapter.CuisineDiffCallback(), this);
        dataBinding.couisinesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dataBinding.couisinesRecyclerview.setAdapter(cuisinesAdapter);

        // TODO: 1.07.2018 city_id provides shared preferences after get the user location
        viewModel.getCuisines(59, null, null, TAKEN_CUISINES).observe(this, response -> {
            cuisinesAdapter.submitList(response.getCuisines());
        });

        nearbyAdapter = new NearbyRestaurantRecylerAdapter(new NearbyRestaurantRecylerAdapter.NearbyRestaurantsDiffCallback(), this);
        dataBinding.nearbyRestaurantsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.nearbyRestaurantsRecyclerview.setAdapter(nearbyAdapter);

        // TODO: 2.07.2018 lat lon provides by LocationManager
        viewModel.getNearbyRestaurants(51.507, -0.1277, TAKEN_NEARBY_RESTAURANTS).observe(this, response -> {
            nearbyAdapter.submitList(response.getNearbyRestaurants());
        });
    }

    @Override
    public void onMainCuisineClick(Cuisine cuisine) {

    }

    @Override
    public void onMainNearbyRestaurantsClick(NearbyRestaurant nearbyRestaurant) {

    }
}

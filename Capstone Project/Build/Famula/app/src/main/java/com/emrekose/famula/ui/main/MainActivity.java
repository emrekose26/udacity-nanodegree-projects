package com.emrekose.famula.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityMainBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.ui.cuisineslist.CuisinesListActivity;

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

        setupToolbar();

        dataBinding.setLifecycleOwner(this);

        cuisinesAdapter = new CuisinesRecyclerAdapter(new CuisinesRecyclerAdapter.CuisineDiffCallback(), this);
        dataBinding.couisinesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dataBinding.couisinesRecyclerview.setAdapter(cuisinesAdapter);

        // TODO: 1.07.2018 city_id provides shared preferences after get the user location
        viewModel.getCuisines(59, null, null, TAKEN_CUISINES).observe(this, response -> {
            dataBinding.setCuisineSize(response.size());
            cuisinesAdapter.submitList(response);
        });

        nearbyAdapter = new NearbyRestaurantRecylerAdapter(new NearbyRestaurantRecylerAdapter.NearbyRestaurantsDiffCallback(), this);
        dataBinding.nearbyRestaurantsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.nearbyRestaurantsRecyclerview.setAdapter(nearbyAdapter);

        // TODO: 2.07.2018 lat lon provides by LocationManager
        viewModel.getNearbyRestaurants(51.507, -0.1277, TAKEN_NEARBY_RESTAURANTS).observe(this, response -> {
            dataBinding.setRestaurantSize(response.size());
            nearbyAdapter.submitList(response);
        });

        dataBinding.viewAllCuisine.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CuisinesListActivity.class));
        });
    }

    @Override
    public void onMainCuisineClick(Cuisine cuisine) {

    }

    @Override
    public void onMainNearbyRestaurantsClick(NearbyRestaurant nearbyRestaurant) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_my_location:
                // TODO: 4.07.2018 get the user location
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}

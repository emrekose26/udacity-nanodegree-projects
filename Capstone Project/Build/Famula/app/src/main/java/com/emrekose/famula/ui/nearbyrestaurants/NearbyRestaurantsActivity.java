package com.emrekose.famula.ui.nearbyrestaurants;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityNearbyRestaurantsBinding;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.ui.main.MainViewModel;

public class NearbyRestaurantsActivity extends BaseOnlyActivity<ActivityNearbyRestaurantsBinding, MainViewModel> implements NearbyRestaurantsCallback {

    private NearbyRestaurantsAdapter adapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_nearby_restaurants;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        adapter = new NearbyRestaurantsAdapter(new NearbyRestaurantsAdapter.NearbyRestaurantsDiffCallback(), this);
        dataBinding.nearbyRestaurantsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.nearbyRestaurantsRecyclerview.setAdapter(adapter);

        // TODO: 4.07.2018 lat lon provides by LocationManager
        viewModel.getNearbyRestaurants(51.507, -0.1277, null).observe(this, response -> {
            dataBinding.setListSize(response.size());
            adapter.submitList(response);
        });
    }

    @Override
    public void onNearbyRestaurantClick(NearbyRestaurant restaurant) {
        // TODO: 4.07.2018 start restaurant detail activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.nearbyRestaurantsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.nearby_restaurants));
    }
}

package com.emrekose.famula.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.data.local.entity.CommonRestaurant;
import com.emrekose.famula.databinding.ActivityFavoritesBinding;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.ui.detail.RestaurantDetailViewModel;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.LocationUtils;

public class FavoritesActivity extends BaseOnlyActivity<ActivityFavoritesBinding, RestaurantDetailViewModel> implements FavoritesCallback {

    private FavoritesAdapter adapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_favorites;
    }

    @Override
    public Class<RestaurantDetailViewModel> getViewModel() {
        return RestaurantDetailViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        adapter = new FavoritesAdapter(this);
        dataBinding.favoritesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.favoritesRecyclerview.setAdapter(adapter);

        viewModel.getAllFavRestaurants().observe(this, commonRestaurants -> {
            dataBinding.setListSize(commonRestaurants.size());
            adapter.submitList(commonRestaurants);
        });
    }

    @Override
    public void onFavoriteRestaurantClick(CommonRestaurant restaurant) {
        Intent i = new Intent(FavoritesActivity.this, RestaurantDetailActivity.class);
        i.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, restaurant);
        startActivity(i);
    }

    @Override
    public void onFavoriteRestaurantMarkerClick(CommonRestaurant restaurant) {
        LocationUtils.openGoogleMaps(this, Double.parseDouble(restaurant.getLatitude()), Double.parseDouble(restaurant.getLongitude()));
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.favoritesToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.favorites));
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

}

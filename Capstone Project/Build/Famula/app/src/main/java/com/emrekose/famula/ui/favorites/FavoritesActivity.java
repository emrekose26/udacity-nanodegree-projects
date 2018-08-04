package com.emrekose.famula.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.ImageView;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.data.local.entity.CommonRestaurant;
import com.emrekose.famula.databinding.ActivityFavoritesBinding;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.ui.detail.RestaurantDetailViewModel;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.LocationUtils;
import com.emrekose.famula.widget.AppWidgetHelper;

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

        AppWidgetHelper.updateAppWidget(this);

        adapter = new FavoritesAdapter(this);
        dataBinding.favoritesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.favoritesRecyclerview.setAdapter(adapter);

        viewModel.getAllFavRestaurants().observe(this, commonRestaurants -> {
            dataBinding.setListSize(commonRestaurants.size());
            adapter.submitList(commonRestaurants);
        });
    }

    @Override
    public void onFavoriteRestaurantClick(CommonRestaurant restaurant, ImageView sharedElement) {
        Intent i = new Intent(FavoritesActivity.this, RestaurantDetailActivity.class);
        i.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, restaurant);
        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElement, getString(R.string.shared_element_transition_name)).toBundle();
        startActivity(i, options);
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

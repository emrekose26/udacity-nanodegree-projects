package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesRestauBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.LocationUtils;
import com.emrekose.famula.util.SPUtils;

import javax.inject.Inject;

import timber.log.Timber;

public class CuisinesRestauActivity extends BaseOnlyActivity<ActivityCuisinesRestauBinding, CuisinesRestauViewModel>
implements CuisinesRestauCallback {

    private CuisinesRestauAdapter adapter;
    private String cuisineId;
    private Cuisine cuisine;

    @Inject
    SharedPreferences preferences;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_cuisines_restau;
    }

    @Override
    public Class<CuisinesRestauViewModel> getViewModel() {
        return CuisinesRestauViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            cuisine = (Cuisine) getIntent().getExtras().getSerializable(Constants.CUISINES_BUNDLE_KEY);
            cuisineId = String.valueOf(cuisine.getCuisine().getCuisineId());
        }

        setupToolbar(cuisine);

        adapter = new CuisinesRestauAdapter(this);
        dataBinding.cuisinesRestaurantRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.cuisinesRestaurantRecyclerview.setAdapter(adapter);

        int entityId = SPUtils.getIntegerPreference(preferences, Constants.ENTITY_ID, 0);
        String entityType = SPUtils.getStringPreference(preferences, Constants.ENTITY_TYPE);

        viewModel.getRestaurants(entityId, entityType, cuisineId).observe(this, restaurants -> {
            dataBinding.setListSize(restaurants.size());
            adapter.submitList(restaurants);

            Timber.e(String.valueOf(restaurants.size()));
        });
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant, ImageView sharedElement) {
        Intent i = new Intent(CuisinesRestauActivity.this, RestaurantDetailActivity.class);
        i.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, restaurant);
        Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElement, getString(R.string.shared_element_transition_name)).toBundle();
        startActivity(i, options);
    }

    @Override
    public void onRestaurantMarkerClick(Restaurant restaurant) {
        LocationUtils.openGoogleMaps(this, Double.parseDouble(restaurant.getRestaurant().getLocation().getLatitude()), Double.parseDouble(restaurant.getRestaurant().getLocation().getLongitude()));
    }

    private void setupToolbar(Cuisine cuisine) {
        setSupportActionBar(dataBinding.cuisinesRestaurantToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(cuisine.getCuisine().getCuisineName());
    }
}

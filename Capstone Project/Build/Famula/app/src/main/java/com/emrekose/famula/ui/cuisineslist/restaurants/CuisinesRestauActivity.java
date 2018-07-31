package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesRestauBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.EntityType;

import timber.log.Timber;

public class CuisinesRestauActivity extends BaseOnlyActivity<ActivityCuisinesRestauBinding, CuisinesRestauViewModel>
implements CuisinesRestauCallback {

    private CuisinesRestauAdapter adapter;
    private String cuisineId;
    private Cuisine cuisine;

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

        // TODO: 24.07.2018 entity id, entity type, cuisine id will take
        Timber.e("cuisineId " + cuisineId);

        viewModel.getRestaurants(59, EntityType.CITY.getType(), "82").observe(this, restaurants -> {
            dataBinding.setListSize(restaurants.size());
            adapter.submitList(restaurants);

            Timber.e(String.valueOf(restaurants.size()));
        });
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        Intent i = new Intent(CuisinesRestauActivity.this, RestaurantDetailActivity.class);
        i.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, restaurant);
        startActivity(i);
    }

    private void setupToolbar(Cuisine cuisine) {
        setSupportActionBar(dataBinding.cuisinesRestaurantToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(cuisine.getCuisine().getCuisineName());
    }
}

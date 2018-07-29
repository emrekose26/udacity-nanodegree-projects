package com.emrekose.famula.ui.cuisineslist.restaurants;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesRestauBinding;
import com.emrekose.famula.util.EntityType;

import timber.log.Timber;

public class CuisinesRestauActivity extends BaseOnlyActivity<ActivityCuisinesRestauBinding, CuisinesRestauViewModel> {

    private CuisinesRestauAdapter adapter;

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

        setupToolbar();

        adapter = new CuisinesRestauAdapter();
        dataBinding.cuisinesRestaurantRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.cuisinesRestaurantRecyclerview.setAdapter(adapter);

        // TODO: 24.07.2018 entity id, entity type, cuisine id will take
        viewModel.getRestaurants(59, EntityType.CITY.getType(), "82").observe(this, restaurants -> {
            dataBinding.setListSize(restaurants.size());
            adapter.submitList(restaurants);

            Timber.e(String.valueOf(restaurants.size()));
        });
    }



    private void setupToolbar() {
        setSupportActionBar(dataBinding.cuisinesRestaurantToolbar);
    }
}

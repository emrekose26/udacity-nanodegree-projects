package com.emrekose.famula.ui.nearbyrestaurants;

import android.os.Bundle;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityNearbyRestaurantsBinding;
import com.emrekose.famula.ui.main.MainViewModel;

public class NearbyRestaurantsActivity extends BaseOnlyActivity<ActivityNearbyRestaurantsBinding, MainViewModel> {

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


    }
}

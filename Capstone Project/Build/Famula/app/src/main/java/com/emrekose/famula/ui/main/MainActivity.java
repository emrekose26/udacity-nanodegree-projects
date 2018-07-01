package com.emrekose.famula.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityMainBinding;
import com.emrekose.famula.model.cuisines.Cuisine;

public class MainActivity extends BaseOnlyActivity<ActivityMainBinding, MainViewModel> implements CuisinesCallback {

    private static final int TAKEN_CUISINES = 10;

    private CuisinesRecyclerAdapter adapter;

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

        adapter = new CuisinesRecyclerAdapter(new CuisinesRecyclerAdapter.CuisineDiffCallback(), this);
        dataBinding.couisinesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dataBinding.couisinesRecyclerview.setAdapter(adapter);

        // TODO: 1.07.2018 city_id provides shared preferences after get the user location
        viewModel.getCuisines(59, null, null, TAKEN_CUISINES).observe(this, response -> {
            adapter.submitList(response.getCuisines());
        });
    }

    @Override
    public void onMainCuisineClick(Cuisine cuisine) {

    }
}

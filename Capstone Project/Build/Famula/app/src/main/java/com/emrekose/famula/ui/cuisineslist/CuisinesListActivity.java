package com.emrekose.famula.ui.cuisineslist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesListBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.ui.cuisineslist.restaurants.CuisinesRestauActivity;
import com.emrekose.famula.ui.main.MainViewModel;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.SPUtils;

import javax.inject.Inject;

public class CuisinesListActivity extends BaseOnlyActivity<ActivityCuisinesListBinding, MainViewModel> implements CuisinesListCallback {

    private CuisinesListRecyclerAdapter adapter;

    @Inject
    SharedPreferences preferences;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_cuisines_list;
    }

    @Override
    public Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();
        
        adapter = new CuisinesListRecyclerAdapter(this);
        dataBinding.cuisinesListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.cuisinesListRecyclerview.setAdapter(adapter);

        int cityId = SPUtils.getIntegerPreference(preferences, Constants.CITY_ID, 0);
        viewModel.getCuisines(cityId, null, null, null).observe(this, response -> {
            dataBinding.setListSize(response.size());
            adapter.submitList(response);
        });
    }

    @Override
    public void onCouisineClick(Cuisine cuisine) {
        Intent intent = new Intent(CuisinesListActivity.this, CuisinesRestauActivity.class);
        intent.putExtra(Constants.CUISINES_BUNDLE_KEY, cuisine);
        startActivity(intent);
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
        setSupportActionBar(dataBinding.cuisinesListToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.cuisines));
    }
}

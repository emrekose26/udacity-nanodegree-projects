package com.emrekose.famula.ui.cuisineslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesListBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.ui.cuisineslist.restaurants.CuisinesRestauActivity;
import com.emrekose.famula.ui.main.MainViewModel;

public class CuisinesListActivity extends BaseOnlyActivity<ActivityCuisinesListBinding, MainViewModel> implements CuisinesListCallback {

    private CuisinesListRecyclerAdapter adapter;

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

        viewModel.getCuisines(59, null, null, null).observe(this, response -> {
            dataBinding.setListSize(response.size());
            adapter.submitList(response);
        });
    }

    @Override
    public void onCouisineClick(Cuisine cuisine) {
        Intent intent = new Intent(CuisinesListActivity.this, CuisinesRestauActivity.class);
        // TODO: 24.07.2018 putExtra -> cuisine
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

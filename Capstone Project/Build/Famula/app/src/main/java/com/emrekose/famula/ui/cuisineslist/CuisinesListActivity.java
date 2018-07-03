package com.emrekose.famula.ui.cuisineslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityCuisinesListBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
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

        adapter = new CuisinesListRecyclerAdapter(new CuisinesListRecyclerAdapter.CuisinesListDiffCallback(), this);
        dataBinding.cuisinesListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.cuisinesListRecyclerview.setAdapter(adapter);

        viewModel.getCuisines(59, null, null, null).observe(this, response -> {
            if (response.getCuisines().size() > 0) {
                adapter.submitList(response.getCuisines());
                dataBinding.cuisinesListProgressbar.setVisibility(View.GONE);
            } else {
                dataBinding.cuisinesListProgressbar.setVisibility(View.VISIBLE);
                dataBinding.cuisinesListRecyclerview.setVisibility(View.GONE);
            }

        });
    }

    @Override
    public void onCouisineClick(Cuisine cuisine) {
        // TODO: 3.07.2018 start restaurants activity
    }
}

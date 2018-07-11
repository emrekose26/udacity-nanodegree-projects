package com.emrekose.famula.ui.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseActivity;
import com.emrekose.famula.common.ViewPagerAdapter;
import com.emrekose.famula.databinding.ActivityRestaurantDetailBinding;

public class RestaurantDetailActivity extends BaseActivity<ActivityRestaurantDetailBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_restaurant_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();
        setupViewPager(dataBinding.detailViewpager);
        dataBinding.detailTabs.setupWithViewPager(dataBinding.detailViewpager);
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(RestaurantInfoFragment.newInstance(), getString(R.string.info));
        pagerAdapter.addFragment(RestaurantReviewsFragment.newInstance(), getString(R.string.reviews));
        pagerAdapter.addFragment(RestaurantMapFragment.newInstance(), getString(R.string.map));
        viewPager.setAdapter(pagerAdapter);
    }
}

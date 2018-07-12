package com.emrekose.famula.ui.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restaurant_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add_to_favorites:
                // TODO: 12.07.2018 add to favorites
                break;
            case R.id.menu_action_share:
                // TODO: 12.07.2018 share restaurant adress
                break;
        }
        return super.onOptionsItemSelected(item);
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

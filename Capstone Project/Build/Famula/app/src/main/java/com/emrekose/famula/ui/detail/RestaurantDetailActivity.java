package com.emrekose.famula.ui.detail;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyFragmentActivity;
import com.emrekose.famula.common.ViewPagerAdapter;
import com.emrekose.famula.data.local.entity.CommonRestaurant;
import com.emrekose.famula.databinding.ActivityRestaurantDetailBinding;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.util.Constants;

import timber.log.Timber;

public class RestaurantDetailActivity extends BaseOnlyFragmentActivity<ActivityRestaurantDetailBinding, RestaurantDetailViewModel> {

    private CommonRestaurant commonRestaurant;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_restaurant_detail;
    }

    @Override
    public Class<RestaurantDetailViewModel> getViewModel() {
        return RestaurantDetailViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        if (getIntent() != null) {
            if (getIntent().getExtras().getSerializable(Constants.RESTAURANTS_BUNDLE_KEY) instanceof NearbyRestaurant) {
                NearbyRestaurant nearbyRestaurant = (NearbyRestaurant) getIntent().getExtras().getSerializable(Constants.RESTAURANTS_BUNDLE_KEY);

                setCommonRestaurant(nearbyRestaurant.getRestaurant().getId(), nearbyRestaurant.getRestaurant().getName(), nearbyRestaurant.getRestaurant().getUrl(), nearbyRestaurant.getRestaurant().getLocation().getAddress(),
                        nearbyRestaurant.getRestaurant().getLocation().getLocality(), nearbyRestaurant.getRestaurant().getLocation().getLatitude(), nearbyRestaurant.getRestaurant().getLocation().getLongitude(),
                        nearbyRestaurant.getRestaurant().getCuisines(), nearbyRestaurant.getRestaurant().getAverageCostForTwo(), nearbyRestaurant.getRestaurant().getPriceRange(),
                        nearbyRestaurant.getRestaurant().getCurrency(), nearbyRestaurant.getRestaurant().getThumb(), nearbyRestaurant.getRestaurant().getUserRating().getAggregateRating(),
                        nearbyRestaurant.getRestaurant().getUserRating().getRatingColor(), nearbyRestaurant.getRestaurant().getFeaturedImage(), nearbyRestaurant.getRestaurant().getHasOnlineDelivery(), nearbyRestaurant.getRestaurant().getHasTableBooking());
            } else if (getIntent().getExtras().getSerializable(Constants.RESTAURANTS_BUNDLE_KEY) instanceof Restaurant) {

            }
        }

        setupViewPager(dataBinding.detailViewpager, commonRestaurant);

        dataBinding.detailTabs.setupWithViewPager(dataBinding.detailViewpager);

        dataBinding.setRestaurant(commonRestaurant);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restaurant_detail, menu);
        isFav(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add_to_favorites:
                updateFavImage(item);
                break;
            case R.id.menu_action_share:
                // TODO: 12.07.2018 share restaurant adress
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void isFav(Menu menu) {
        viewModel.isFav(commonRestaurant.getId()).observe(this, isFav -> {
            if (!isFav) {
                menu.getItem(1).setIcon(R.drawable.ic_star_empty);
            } else {
                menu.getItem(1).setIcon(R.drawable.ic_star_full);
            }
        });
    }

    private void updateFavImage(MenuItem item) {
        final boolean[] isFav = {false};
        viewModel.isFav(commonRestaurant.getId()).observe(this, aBoolean -> isFav[0] = aBoolean);

        Timber.e(String.valueOf(isFav[0]));
        if (!isFav[0]) {
            viewModel.addRestaurantToFavorite(commonRestaurant);
            Toast.makeText(this, getString(R.string.added_to_favs), Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_star_full);
            viewModel.isFav(commonRestaurant.getId()).removeObservers(this);
        } else {
            viewModel.deleteRestaurantFromFavorites(commonRestaurant);
            Toast.makeText(this, getString(R.string.deleted_from_fav), Toast.LENGTH_SHORT).show();
            item.setIcon(R.drawable.ic_star_empty);
            viewModel.isFav(commonRestaurant.getId()).removeObservers(this);
        }

    }

    private void setCommonRestaurant(String id, String name, String url, String address, String locality, String lat, String lon, String cuisines, int averageCostForTwo,
                                     int priceRange, String currency, String thumb, String aggregateRating, String ratingColor, String featuredImage, int hasOnlineDelivery, int hasTableBooking) {

        commonRestaurant = new CommonRestaurant();
        commonRestaurant.setId(id);
        commonRestaurant.setName(name);
        commonRestaurant.setUrl(url);
        commonRestaurant.setAddress(address);
        commonRestaurant.setLocality(locality);
        commonRestaurant.setLatitude(lat);
        commonRestaurant.setLongitude(lon);
        commonRestaurant.setCuisines(cuisines);
        commonRestaurant.setAverageCostForTwo(averageCostForTwo);
        commonRestaurant.setPriceRange(priceRange);
        commonRestaurant.setCurrency(currency);
        commonRestaurant.setThumb(thumb);
        commonRestaurant.setAggregateRating(aggregateRating);
        commonRestaurant.setRatingColor(ratingColor);
        commonRestaurant.setFeaturedImage(featuredImage);
        commonRestaurant.setHasOnlineDelivery(hasOnlineDelivery);
        commonRestaurant.setHasTableBooking(hasTableBooking);

        appBarBehaviour(commonRestaurant);
    }

    private void appBarBehaviour(CommonRestaurant restaurant) {
        dataBinding.detailAppbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                dataBinding.detailToolbar.setTitle(restaurant.getName());
                //dataBinding.detailToolbar.setSubtitle(restaurant.getAddress());
            } else {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                dataBinding.detailToolbar.setTitle(" ");
            }
        });
        dataBinding.detailCollapsing.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupViewPager(ViewPager viewPager, CommonRestaurant commonRestaurant) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(RestaurantInfoFragment.newInstance(commonRestaurant), getString(R.string.info));
        pagerAdapter.addFragment(RestaurantReviewsFragment.newInstance(commonRestaurant), getString(R.string.reviews));
        pagerAdapter.addFragment(RestaurantMapFragment.newInstance(commonRestaurant), getString(R.string.map));
        viewPager.setAdapter(pagerAdapter);
    }
}

package com.emrekose.famula.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityMainBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.ui.cuisineslist.CuisinesListActivity;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.ui.establisments.EstablismentsActivity;
import com.emrekose.famula.ui.nearbyrestaurants.NearbyRestaurantsActivity;
import com.emrekose.famula.util.Constants;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import timber.log.Timber;

public class MainActivity extends BaseOnlyActivity<ActivityMainBinding, MainViewModel>
        implements NavigationView.OnNavigationItemSelectedListener, CuisinesCallback, NearbyRestaurantsMainCallback,
        LocationCallback, EasyPermissions.PermissionCallbacks {

    private static final int TAKEN_CUISINES = 10;
    private static final int TAKEN_NEARBY_RESTAURANTS = 5;
    private static final int LOCATION_PERM_CODE = 101;

    private CuisinesRecyclerAdapter cuisinesAdapter;
    private NearbyRestaurantsMainAdapter nearbyAdapter;


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

        setupToolbar();
        navViewConfig();

        dataBinding.setLifecycleOwner(this);

        cuisinesAdapter = new CuisinesRecyclerAdapter(new CuisinesRecyclerAdapter.CuisineDiffCallback(), this);
        dataBinding.couisinesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dataBinding.couisinesRecyclerview.setAdapter(cuisinesAdapter);

        // TODO: 1.07.2018 city_id provides shared preferences after get the user location
        viewModel.getCuisines(59, null, null, TAKEN_CUISINES).observe(this, response -> {
            dataBinding.setCuisineSize(response.size());
            cuisinesAdapter.submitList(response);
        });

        nearbyAdapter = new NearbyRestaurantsMainAdapter(new NearbyRestaurantsMainAdapter.NearbyRestaurantsDiffCallback(), this);
        dataBinding.nearbyRestaurantsMainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.nearbyRestaurantsMainRecyclerview.setAdapter(nearbyAdapter);

        // TODO: 2.07.2018 lat lon provides by LocationManager
        viewModel.getNearbyRestaurants(51.507, -0.1277, TAKEN_NEARBY_RESTAURANTS).observe(this, response -> {
            dataBinding.setRestaurantSize(response.size());
            nearbyAdapter.submitList(response);
        });

        dataBinding.viewAllCuisine.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CuisinesListActivity.class));
        });

        dataBinding.viewAllNearbyRestaurants.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NearbyRestaurantsActivity.class));
        });
    }

    @Override
    public void onMainCuisineClick(Cuisine cuisine) {

    }

    @Override
    public void onMainNearbyRestaurantsClick(NearbyRestaurant nearbyRestaurant) {
        Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
        intent.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, nearbyRestaurant);
        startActivity(intent);
    }

    @Override
    public void onCurrentLocationClick() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            currentLocationConfig();
        } else {
            gpsSettings();
        }
    }

    @AfterPermissionGranted(LOCATION_PERM_CODE)
    private void currentLocationConfig() {
        boolean hasLocationPermission = EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasLocationPermission) {
            gpsSettings();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.location_permission_warning),
                    LOCATION_PERM_CODE, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    private void gpsSettings() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.enable_gps))
                    .setMessage(getString(R.string.require_gps_message))
                    .setPositiveButton(getString(android.R.string.yes), (dialog, which) ->
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                    .setNegativeButton(android.R.string.no, (dialog, which) -> {  /* do nothing */ })
                    .show();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Timber.e("onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Timber.e("onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dataBinding.mainDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_action_my_location:
                showLocationBottomSheet();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_establisments:
                startActivity(new Intent(MainActivity.this, EstablismentsActivity.class));
                break;
        }
        dataBinding.mainDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (dataBinding.mainDrawer.isDrawerOpen(GravityCompat.START)) {
            dataBinding.mainDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void navViewConfig() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dataBinding.mainDrawer, dataBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dataBinding.mainDrawer.setDrawerListener(toggle);
        toggle.syncState();

        dataBinding.mainNavView.setNavigationItemSelectedListener(this);
    }

    private void showLocationBottomSheet() {
        LocationBottomSheetFragment bottomSheetFragment = new LocationBottomSheetFragment(this);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}

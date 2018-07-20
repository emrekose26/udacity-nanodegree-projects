package com.emrekose.famula.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
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
import android.widget.Toast;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivityMainBinding;
import com.emrekose.famula.model.cuisines.Cuisine;
import com.emrekose.famula.model.geocode.NearbyRestaurant;
import com.emrekose.famula.model.locations.LocationSuggestion;
import com.emrekose.famula.ui.cuisineslist.CuisinesListActivity;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.ui.establisments.EstablismentsActivity;
import com.emrekose.famula.ui.favorites.FavoritesActivity;
import com.emrekose.famula.ui.nearbyrestaurants.NearbyRestaurantsActivity;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.GPSUtils;
import com.emrekose.famula.util.SPUtils;

import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import timber.log.Timber;

public class MainActivity extends BaseOnlyActivity<ActivityMainBinding, MainViewModel>
        implements NavigationView.OnNavigationItemSelectedListener, CuisinesCallback, NearbyRestaurantsMainCallback,
        FamulaLocationCallback, EasyPermissions.PermissionCallbacks {

    private static final int TAKEN_CUISINES = 10;
    private static final int TAKEN_NEARBY_RESTAURANTS = 5;
    private static final int LOCATION_PERM_CODE = 101;

    private CuisinesRecyclerAdapter cuisinesAdapter;
    private NearbyRestaurantsMainAdapter nearbyAdapter;

    private ProgressDialog progressDialog;

    @Inject
    SharedPreferences preferences;

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


        int cityId = SPUtils.getIntegerPreference(preferences, Constants.CITY_ID, 0);
        // TODO: 17.07.2018 spref city id value null checking
        viewModel.getCuisines(59, null, null, TAKEN_CUISINES).observe(this, response -> {
            dataBinding.setCuisineSize(response.size());
            cuisinesAdapter.submitList(response);
        });

        nearbyAdapter = new NearbyRestaurantsMainAdapter(new NearbyRestaurantsMainAdapter.NearbyRestaurantsDiffCallback(), this);
        dataBinding.nearbyRestaurantsMainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.nearbyRestaurantsMainRecyclerview.setAdapter(nearbyAdapter);

        // TODO: 17.07.2018 spref values null checking
        double lat = SPUtils.getDoublePreference(preferences, Constants.LATITUDE, 0.0);
        double lon = SPUtils.getDoublePreference(preferences, Constants.LONGITUDE, 0.0);

        // TODO: 17.07.2018 get restaurants according to lat lon values
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
            if (!GPSUtils.isGpsEnabled(this)) {
                gpsSettings();
            } else {
                getLastLocation();
            }
        }
    }

    @Override
    public void onSaveLocationClick(String value) {
        getprogressDialog(getString(R.string.validating));
        viewModel.getLocationDatas(value).observe(this, locationSuggestions -> {

            if (locationSuggestions.size() > 0) {
                LocationSuggestion ls = locationSuggestions.get(0);
                String entitType = ls.getEntityType();
                int entityId = ls.getEntityId();
                double latitude = ls.getLatitude();
                double longitude = ls.getLongitude();
                int cityId = ls.getCityId();
                int countryId = ls.getCountryId();

                SPUtils.setStringPreference(preferences, Constants.ENTITY_TYPE, entitType);
                SPUtils.setIntegerPreference(preferences, Constants.ENTITY_ID, entityId);
                SPUtils.setDoublePreferences(preferences, Constants.LATITUDE, latitude);
                SPUtils.setDoublePreferences(preferences, Constants.LONGITUDE, longitude);
                SPUtils.setIntegerPreference(preferences, Constants.CITY_ID, cityId);
                SPUtils.setIntegerPreference(preferences, Constants.COUNTRY_ID, countryId);

            } else {
                Toast.makeText(this, getString(R.string.please_enter_valid_city_name), Toast.LENGTH_SHORT).show();
                viewModel.getLocationDatas(value).removeObservers(this);
            }
            progressDialog.dismiss();
        });
    }

    @AfterPermissionGranted(LOCATION_PERM_CODE)
    private void currentLocationConfig() {
        boolean hasLocationPermission = EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasLocationPermission) {
            if (!GPSUtils.isGpsEnabled(this)) {
                gpsSettings();
            } else {
                getLastLocation();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.location_permission_warning),
                    LOCATION_PERM_CODE, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    private void gpsSettings() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.enable_gps))
                .setMessage(getString(R.string.require_gps_message))
                .setPositiveButton(getString(android.R.string.yes), (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton(android.R.string.no, (dialog, which) -> {  /* do nothing */ })
                .show();
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        getprogressDialog(getString(R.string.finding_your_location)).show();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
            Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Timber.e("lat " + loc.getLatitude() + "lon " + loc.getLongitude());

            SPUtils.setDoublePreferences(preferences, Constants.LATITUDE, loc.getLatitude());
            SPUtils.setDoublePreferences(preferences, Constants.LONGITUDE, loc.getLongitude());

            progressDialog.dismiss();
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Timber.e("lat " + location.getLatitude() + "lon " + location.getLongitude());

                    SPUtils.setDoublePreferences(preferences, Constants.LATITUDE, location.getLatitude());
                    SPUtils.setDoublePreferences(preferences, Constants.LONGITUDE, location.getLongitude());

                    progressDialog.dismiss();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
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
            case R.id.nav_fav_restaurants:
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
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

    private ProgressDialog getprogressDialog(String message) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        return progressDialog;
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

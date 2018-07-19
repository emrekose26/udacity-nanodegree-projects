package com.emrekose.famula.ui.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;
import com.emrekose.famula.data.local.entity.CommonRestaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantMapFragment extends Fragment implements OnMapReadyCallback {

    private static final String MAPS_RESTAURANT_KEY = "maps_restaurant";

    private SupportMapFragment mMapFragment;

    public RestaurantMapFragment() {
        // Required empty public constructor
    }

    public static RestaurantMapFragment newInstance(CommonRestaurant commonRestaurant) {

        Bundle args = new Bundle();
        args.putSerializable(MAPS_RESTAURANT_KEY, commonRestaurant);

        RestaurantMapFragment fragment = new RestaurantMapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_map, container, false);

        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_container);
        mMapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CommonRestaurant restaurant = (CommonRestaurant) getArguments().getSerializable(MAPS_RESTAURANT_KEY);

        LatLng marker = new LatLng(Double.parseDouble(restaurant.getLatitude()), Double.parseDouble(restaurant.getLongitude()));
        googleMap.addMarker(new MarkerOptions().position(marker)
                .title(restaurant.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 17.0f));
    }
}

package com.emrekose.famula.ui.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantInfoFragment extends Fragment {


    public RestaurantInfoFragment() {
        // Required empty public constructor
    }

    public static RestaurantInfoFragment newInstance() {

        Bundle args = new Bundle();

        RestaurantInfoFragment fragment = new RestaurantInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_info, container, false);
    }

}

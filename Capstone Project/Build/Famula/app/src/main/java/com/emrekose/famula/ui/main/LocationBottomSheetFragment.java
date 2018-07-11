package com.emrekose.famula.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;

import timber.log.Timber;

public class LocationBottomSheetFragment extends BottomSheetDialogFragment {

    public static LocationBottomSheetFragment newInstance() {
        return new LocationBottomSheetFragment();
    }

    public LocationBottomSheetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_bottom_sheet, container, false);

        view.findViewById(R.id.get_current_location_btn).setOnClickListener(v -> {
            Timber.e("get current location");
        });

        return view;
    }
}

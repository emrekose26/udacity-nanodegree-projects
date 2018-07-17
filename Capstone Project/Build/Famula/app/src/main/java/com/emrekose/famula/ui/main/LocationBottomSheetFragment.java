package com.emrekose.famula.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;

@SuppressLint("ValidFragment")
public class LocationBottomSheetFragment extends BottomSheetDialogFragment {

    private FamulaLocationCallback callback;

    public LocationBottomSheetFragment(FamulaLocationCallback callback) {
        this.callback = callback;
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
            if (callback != null) callback.onCurrentLocationClick();

        });

        return view;
    }
}

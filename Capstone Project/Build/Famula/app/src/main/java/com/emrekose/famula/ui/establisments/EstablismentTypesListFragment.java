package com.emrekose.famula.ui.establisments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseFragment;
import com.emrekose.famula.databinding.FragmentEstablismentTypesListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablismentTypesListFragment extends BaseFragment<EstablismentsViewModel, FragmentEstablismentTypesListBinding> {


    public EstablismentTypesListFragment() {
        // Required empty public constructor
    }


    @Override
    public Class<EstablismentsViewModel> getViewModel() {
        return EstablismentsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_establisment_types_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

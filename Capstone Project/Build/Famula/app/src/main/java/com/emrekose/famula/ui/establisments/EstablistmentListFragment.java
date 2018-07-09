package com.emrekose.famula.ui.establisments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseFragment;
import com.emrekose.famula.databinding.FragmentEstablistmentListBinding;
import com.emrekose.famula.model.establisments.Establishment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablistmentListFragment extends BaseFragment<EstablismentsViewModel, FragmentEstablistmentListBinding> {

    private static final String ESTABLISHMENT_ID_KEY = "establishment_id";

    public static EstablistmentListFragment newInstance(Establishment establistment) {

        Bundle args = new Bundle();

        args.putInt(ESTABLISHMENT_ID_KEY, establistment.getEstablishment().getId());
        EstablistmentListFragment fragment = new EstablistmentListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public EstablistmentListFragment() {
        // Required empty public constructor
    }

    @Override
    public Class<EstablismentsViewModel> getViewModel() {
        return EstablismentsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_establistment_list;
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

        int establishmentId = getArguments().getInt(ESTABLISHMENT_ID_KEY);
    }

}

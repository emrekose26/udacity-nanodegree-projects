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
import com.emrekose.famula.model.establisments.Establishment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablismentTypesListFragment extends BaseFragment<EstablismentsViewModel, FragmentEstablismentTypesListBinding>
        implements EstablismentCallback {

    private EstablismentTypesAdapter adapter;

    public static EstablismentTypesListFragment newInstance() {

        Bundle args = new Bundle();

        EstablismentTypesListFragment fragment = new EstablismentTypesListFragment();
        fragment.setArguments(args);
        return fragment;
    }


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

        adapter = new EstablismentTypesAdapter(new EstablismentTypesAdapter.EstablismentTypesDiffCallback(), this);
        dataBinding.establismentTypesRecyclerview.setAdapter(adapter);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getEstablismentTypes(59, null, null).observe(this, establishments -> {
            dataBinding.setListSize(establishments.size());
            adapter.submitList(establishments);
        });

    }

    @Override
    public void onEstablismentTypesClick(Establishment establishment) {
        // TODO: 7.07.2018 replace establisments list fragment
        ((EstablismentsActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.establisments_container, EstablistmentListFragment.newInstance(establishment))
                .addToBackStack(null)
                .commit();
    }
}

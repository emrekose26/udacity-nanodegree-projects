package com.emrekose.famula.ui.establisments;


import android.content.SharedPreferences;
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
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.SPUtils;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablismentTypesListFragment extends BaseFragment<EstablismentsViewModel, FragmentEstablismentTypesListBinding>
        implements EstablismentCallback.TypesCalback {

    private EstablismentTypesAdapter adapter;

    @Inject
    SharedPreferences preferences;

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

        int cityId = SPUtils.getIntegerPreference(preferences, Constants.CITY_ID, 0);
        viewModel.getEstablismentTypes(cityId, null, null).observe(this, establishments -> {
            dataBinding.setListSize(establishments.size());
            adapter.submitList(establishments);
        });

    }

    @Override
    public void onEstablismentTypesClick(Establishment establishment) {
        ((EstablismentsActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.establisments_container, EstablistmentListFragment.newInstance(establishment))
                .addToBackStack(null)
                .commit();
    }
}

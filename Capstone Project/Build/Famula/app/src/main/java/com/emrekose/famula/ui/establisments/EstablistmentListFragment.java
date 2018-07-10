package com.emrekose.famula.ui.establisments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseFragment;
import com.emrekose.famula.common.RetryCallback;
import com.emrekose.famula.databinding.FragmentEstablistmentListBinding;
import com.emrekose.famula.model.establisments.Establishment;
import com.emrekose.famula.util.EntityType;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablistmentListFragment extends BaseFragment<EstablismentsViewModel, FragmentEstablistmentListBinding>
        implements RetryCallback {

    private static final String ESTABLISHMENT_ID_KEY = "establishment_id";

    private EstablishmentListAdapter adapter;

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

        adapter = new EstablishmentListAdapter(this);
        dataBinding.establishmentListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.establishmentListRecyclerview.setAdapter(adapter);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int establishmentId = getArguments().getInt(ESTABLISHMENT_ID_KEY);

        viewModel.getEstablishmentList(String.valueOf(establishmentId),59, EntityType.CITY.getType()).observe(this, restaurants -> {
            dataBinding.setListSize(restaurants.size());
            adapter.submitList(restaurants);

            Timber.e("fragment" + restaurants.size());
        });
    }

    @Override
    public void retry() {

    }
}

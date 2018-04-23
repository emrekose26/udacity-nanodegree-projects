package com.emrekose.bakingapp.ui.steps;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.Step;
import com.emrekose.bakingapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment {

    @BindView(R.id.step_desc)
    TextView stepDescription;


    public StepsFragment() {
        // Required empty public constructor
    }

    public static StepsFragment newInstance() {

        Bundle args = new Bundle();

        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getIntent() != null) {
            Step step = (Step) getActivity().getIntent().getExtras().getSerializable(Constants.STEPS_EXTRA);

            stepDescription.setText(step.getDescription());
        }
    }
}

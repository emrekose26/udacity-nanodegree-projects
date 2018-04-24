package com.emrekose.bakingapp.ui.steps;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.Step;
import com.emrekose.bakingapp.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment {

    @BindView(R.id.step_desc)
    TextView stepDescription;

    @BindView(R.id.next_step)
    Button nextStepBtn;

    @BindView(R.id.prev_step)
    Button prevStepBtn;

    int stepIndex;


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
            List<Step> stepList = (List<Step>) getActivity().getIntent().getExtras().getSerializable(Constants.STEPS_EXTRA);
            stepIndex = (getActivity().getIntent().getExtras().getInt(Constants.STEPS_INDEX_EXTRA));
            stepDescription.setText(stepList.get(stepIndex).getDescription());

            Intent resultIntent = new Intent();

            nextStepBtn.setOnClickListener(v -> {
                if (stepIndex != stepList.size() - 1) {
                    stepIndex++;
                    stepDescription.setText(stepList.get(stepIndex).getDescription());
                } else {
                    Toast.makeText(getActivity(), "Last Step", Toast.LENGTH_SHORT).show();
                }

                setResult(stepIndex, resultIntent);
            });

            prevStepBtn.setOnClickListener(v -> {
                if (stepIndex != 0) {
                    stepIndex--;
                    stepDescription.setText(stepList.get(stepIndex).getDescription());
                } else {
                    Toast.makeText(getActivity(), "First Step", Toast.LENGTH_SHORT).show();
                }

                setResult(stepIndex, resultIntent);
            });
        }
    }

    private void setResult(int stepIndex, Intent intent) {
        intent.putExtra(Constants.STEP_INDEX_RESULT, stepIndex);
        getActivity().setResult(Activity.RESULT_OK, intent);
    }
}

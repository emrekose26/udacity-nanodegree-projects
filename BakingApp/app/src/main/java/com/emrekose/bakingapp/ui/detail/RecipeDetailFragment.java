package com.emrekose.bakingapp.ui.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.ui.steps.StepsActivity;
import com.emrekose.bakingapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment implements IStepperAdapter {

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeIngredients;

    @BindView(R.id.recipe_steps)
    VerticalStepperView verticalStepperView;

    RecipeResponse response;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance() {

        Bundle args = new Bundle();

        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getIntent() != null) {
            response = (RecipeResponse) getActivity().getIntent().getExtras().getSerializable(Constants.RECIPES_EXTRA);
            setIngredients(response);
            verticalStepperView.setStepperAdapter(this);
        }
    }

    private void setIngredients(RecipeResponse response) {
        recipeIngredients.setText("Ingredients\n");

        for (int i = 0; i < response.getIngredients().size(); i++) {
            String ingredient = response.getIngredients().get(i).getIngredient();
            double quantity = response.getIngredients().get(i).getQuantity();
            String measure = response.getIngredients().get(i).getMeasure();
            String formattedValue = (i + 1) + ". " + ingredient + "(" + quantity + " " + measure + ")" + "\n";
            recipeIngredients.append(formattedValue);
        }
    }

    @NonNull
    @Override
    public CharSequence getTitle(int i) {
        return "Step " + (i + 1);
    }

    @Nullable
    @Override
    public CharSequence getSummary(int i) {
        if (i == 0) {
            return Html.fromHtml(String.format("First step", response.getName()));
        } else {
            if (i != size() - 1) {
                return Html.fromHtml(response.getSteps().get(i - 1).getDescription());
            } else {
                return Html.fromHtml(String.format("Last step", response.getName()));
            }
        }
    }

    @Override
    public int size() {
        return response.getIngredients().size();
    }

    @Override
    public View onCreateCustomView(int index, Context context, VerticalStepperItemView parent) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_item, parent, false);

        TextView contentView = inflateView.findViewById(R.id.item_content);
        contentView.setText(response.getSteps().get(index).getDescription());

        Button nextButton = inflateView.findViewById(R.id.button_next);
        nextButton.setOnClickListener(v -> {
            if (verticalStepperView.canNext()) {
                verticalStepperView.nextStep();
            }
        });

        Button prevButton = inflateView.findViewById(R.id.button_prev);
        prevButton.setOnClickListener(v -> {
            if (index != 0) {
                verticalStepperView.prevStep();
            } else {
                verticalStepperView.setAnimationEnabled(!verticalStepperView.isAnimationEnabled());
            }
        });

        inflateView.setOnClickListener(v -> {
            // TODO: 23.04.2018 two pane check
            Intent intent = new Intent(getActivity(), StepsActivity.class);
            intent.putExtra(Constants.STEPS_EXTRA, response.getSteps().get(index));
            startActivity(intent);
        });

        return inflateView;
    }

    @Override
    public void onShow(int i) {

    }

    @Override
    public void onHide(int i) {

    }
}

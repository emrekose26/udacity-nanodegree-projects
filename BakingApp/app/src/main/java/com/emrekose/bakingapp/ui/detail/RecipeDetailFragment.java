package com.emrekose.bakingapp.ui.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeIngredients;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getIntent() != null) {
            RecipeResponse response = (RecipeResponse) getActivity().getIntent().getExtras().getSerializable(Constants.RECIPES_EXTRA);
            setIngredients(response);
        }
    }

    private void setIngredients(RecipeResponse response) {
        recipeIngredients.setText("Ingredients\n");

        for (int i = 0; i < response.getIngredients().size(); i++) {
            String ingredient = response.getIngredients().get(i).getIngredient();
            double quantity = response.getIngredients().get(i).getQuantity();
            String measure = response.getIngredients().get(i).getMeasure();
            String formattedValue = (i + 1) + ". " + ingredient + "(" + quantity + " " + measure + ")" +"\n";
            recipeIngredients.append(formattedValue);
        }
    }

}

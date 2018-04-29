package com.emrekose.bakingapp.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.utils.Constants;

public class RecipeDetailActivity extends AppCompatActivity {

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        RecipeResponse response = (RecipeResponse) getIntent().getExtras().getSerializable(Constants.RECIPES_EXTRA);


        if (savedInstanceState == null) {
            if (findViewById(R.id.steps_container) != null) {
                mTwoPane = true;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipeDetailContainer, RecipeDetailFragment.newInstance(response))
                        .commit();
            } else {
                mTwoPane = false;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipeDetailContainer, RecipeDetailFragment.newInstance(response))
                        .commit();
            }

        }
    }
}

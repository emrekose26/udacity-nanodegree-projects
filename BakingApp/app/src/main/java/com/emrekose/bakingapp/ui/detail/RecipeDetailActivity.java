package com.emrekose.bakingapp.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emrekose.bakingapp.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);


        if (savedInstanceState == null) {
            // TODO: 21.04.2018 two pane check
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipeDetailContainer, RecipeDetailFragment.newInstance())
                    .commit();
        }
    }
}

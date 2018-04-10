package com.emrekose.bakingapp.ui.recipes;

import android.os.Bundle;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            // TODO: 10.04.2018 two pane check
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, RecipeListFragment.newInstance())
                    .commit();
        }
    }
}

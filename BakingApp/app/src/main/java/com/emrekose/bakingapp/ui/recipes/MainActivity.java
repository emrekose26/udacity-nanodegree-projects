package com.emrekose.bakingapp.ui.recipes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, RecipeListFragment.newInstance())
                    .commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle(R.string.app_name);
    }
}

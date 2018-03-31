package com.emrekose.popularmoviesstage2.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainToolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();

       if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.main_container, MainFragment.newInstance())
                   .commit();
       }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }
}

package com.emrekose.bakingapp.ui.steps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.emrekose.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    @BindView(R.id.steps_toolbar)
    Toolbar stepsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        setupToolbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_container, StepsFragment.newInstance(null))
                    .commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(stepsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

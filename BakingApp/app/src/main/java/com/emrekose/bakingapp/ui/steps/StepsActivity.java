package com.emrekose.bakingapp.ui.steps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.emrekose.bakingapp.R;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (savedInstanceState == null) {
            // TODO: 23.04.2018 two pane check
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_container, StepsFragment.newInstance())
                    .commit();
        }
    }
}

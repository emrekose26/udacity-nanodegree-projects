package com.emrekose.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        if (getIntent() != null) {
            String joke = getIntent().getStringExtra("joke");
            TextView jokeText = findViewById(R.id.joke_display_textView);
            jokeText.setText(joke);
        }
    }
}

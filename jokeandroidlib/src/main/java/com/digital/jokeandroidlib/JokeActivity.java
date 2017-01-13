package com.digital.jokeandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tassioauad.jokeandroidlib.R;

public class JokeActivity extends AppCompatActivity {


    public static final String EXTRA_JOKE = "extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String joke = extras.getString(JokeActivity.EXTRA_JOKE);
            ((TextView)findViewById(R.id.joke_tv)).setText(joke);
        }

    }
}


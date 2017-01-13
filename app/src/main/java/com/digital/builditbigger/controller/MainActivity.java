package com.digital.builditbigger.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.digital.builditbigger.MainFragment;
import com.digital.builditbigger.R;
import com.digital.builditbigger.api.JokeAsyncTask;
import com.digital.builditbigger.api.OnJokeReceiveListener;
import com.digital.jokeandroidlib.JokeActivity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnJokeReceiveListener {


    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_main);
        /*findViewById(R.id.click).setOnClickListener(this);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);*/
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment,new MainFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        showProgress("loading");
        new JokeAsyncTask(this).execute();
    }

    @Override
    public void onGetJoke(String joke) {
        hideProgress();
        if (joke == null) {
            joke = "Sorry no Jokes Today";
        } else if (joke.contains("fail")) {
            joke = "Sorry no Jokes Today";
        }
        startActivity(new Intent(this, JokeActivity.class).putExtra(JokeActivity.EXTRA_JOKE, joke));
    }

    public void showProgress(String msg) {
        if (mProgressBar == null) {
            mProgressBar = new ProgressDialog(this);
            mProgressBar.setCancelable(false);
            mProgressBar.getWindow().setGravity(Gravity.CENTER);
            mProgressBar.setMessage(msg);
            mProgressBar.setIndeterminate(true);
        }

        if (!mProgressBar.isShowing()) {
            mProgressBar.show();
        }
    }

    public void hideProgress() {
        if (getMainLooper().getThread().equals(Thread.currentThread())) {

            hideProgressInternal();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressInternal();
                }
            });
        }

    }

    private void hideProgressInternal() {
        if (mProgressBar != null && mProgressBar.isShowing() && !isFinishing()) {
            mProgressBar.dismiss();
        }
    }
}

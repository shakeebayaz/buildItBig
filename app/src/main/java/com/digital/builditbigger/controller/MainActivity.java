package com.digital.builditbigger.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.digital.builditbigger.R;
public class MainActivity extends AppCompatActivity  {


    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment,new MainFragment()).commit();
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

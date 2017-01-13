package com.digital.builditbigger.controller;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.builditbigger.R;
import com.digital.builditbigger.api.JokeAsyncTask;
import com.digital.builditbigger.api.OnJokeReceiveListener;
import com.digital.builditbigger.databinding.FragmentMainBinding;
import com.digital.jokeandroidlib.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainFragment extends BaseFragment implements View.OnClickListener, OnJokeReceiveListener {

    private FragmentMainBinding mBinding;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mBinding.adView.loadAd(adRequest);

        return mBinding.getRoot();
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
        startActivity(new Intent(mContext, JokeActivity.class).putExtra(JokeActivity.EXTRA_JOKE, joke));
    }
}

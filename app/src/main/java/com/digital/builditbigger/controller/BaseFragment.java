package com.digital.builditbigger.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.digital.builditbigger.R;


/**
 * Created by Shakeeb on 23/08/16.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;
    private ProgressDialog mProgress;
    protected Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    protected void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    protected void showProgress() {
        showProgress(getString(R.string.progress_msg));
    }

    protected void showProgress(String msg) {
        if (mProgress == null) {
            mProgress = new ProgressDialog(mContext);
            mProgress.setCancelable(false);
            mProgress.getWindow().setGravity(Gravity.CENTER);
            mProgress.setMessage(msg);
            mProgress.setIndeterminate(true);
        }

        if (!mProgress.isShowing()) {
            mProgress.show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    protected void hideProgress() {
        if (mContext.getMainLooper().getThread().equals(Thread.currentThread())) {

            hideProgressInternal();
        } else {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressInternal();
                }
            });
        }

    }

    protected void hideProgressInternal() {
        if (mProgress != null && mProgress.isShowing() && !((Activity) mContext).isFinishing()) {
            mProgress.dismiss();
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }



}

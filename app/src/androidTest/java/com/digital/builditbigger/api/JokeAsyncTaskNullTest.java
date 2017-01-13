package com.digital.builditbigger.api;

import android.test.AndroidTestCase;


public class JokeAsyncTaskNullTest extends AndroidTestCase {


    public void test() {
        try {
        JokeAsyncTask endpointsAsyncTask = new JokeAsyncTask(new OnJokeReceiveListener() {
            @Override
            public void onGetJoke(String joke) {
                assertNotNull(joke);
            }
        });
        endpointsAsyncTask.execute();

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
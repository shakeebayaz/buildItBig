package com.digital.builditbigger.api;


import android.os.AsyncTask;

import com.digital.jokebackend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private JokeApi mjokeApi = null;
    String ROOT_URL="http://10.0.2.2:8080/_ah/api/";
    private OnJokeReceiveListener onJokeReceiveListener;

    public JokeAsyncTask(OnJokeReceiveListener onJokeReceiveListener) {
        this.onJokeReceiveListener = onJokeReceiveListener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (mjokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mjokeApi = builder.build();
        }
        try {
            return mjokeApi.joke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(result==null) {
            onJokeReceiveListener.onGetJoke("sorry no more jokes");
        }else if(result!=null && result.contains("fail")){
            onJokeReceiveListener.onGetJoke("sorry no more jokes");
        }else {
            onJokeReceiveListener.onGetJoke(result);
        }
    }

}

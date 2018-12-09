package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import buzz.kautilya.com.bridge.AttackActivity;

/**
 * Created by Kautilya on 27-11-2018.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private EndpointsAsyncTaskInteractor endpointsAsyncTaskInteractor;

    public EndpointsAsyncTask(EndpointsAsyncTaskInteractor endpointsAsyncTaskInteractor) {
        this.endpointsAsyncTaskInteractor = endpointsAsyncTaskInteractor;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://connect-94efb.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.sayHi("").execute().getData();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        endpointsAsyncTaskInteractor.result(result);

    }

    interface EndpointsAsyncTaskInteractor {
        void result(String name);
    }
}
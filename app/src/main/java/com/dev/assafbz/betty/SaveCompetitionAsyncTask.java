package com.dev.assafbz.betty;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.dev.assafbz.betty.backend.competitionApi.CompetitionApi;
import com.dev.assafbz.betty.backend.competitionApi.model.Competition;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by assafbz on 18/06/2015.
 */
class SaveCompetitionAsyncTask extends AsyncTask<Pair<Context, Competition>, Void, String> {
    private static CompetitionApi competitionApiService = null;
    private Context context;

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, Competition>... params) {
        if(competitionApiService == null) {  // Only do this once
            CompetitionApi.Builder builder = new CompetitionApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.0.197:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            competitionApiService = builder.build();
        }

        context = params[0].first;
        Competition competition = params[0].second;

        try {
            competitionApiService.insert(competition).execute();
            return "Competition Saved";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
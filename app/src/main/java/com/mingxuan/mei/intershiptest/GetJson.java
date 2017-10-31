package com.mingxuan.mei.intershiptest;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Mingxuan on 10/31/2017.
 * Retrieve Json from dribbble using HttpsURLConnection.
 * cURL command:
 * curl -H "Authorization: Bearer a62b88ea291c0d0e5b9295fdb8930936f945027bb84ff747ef6b89f8a9cd4da1" https://api.dribbble.com/v1/shots?sort=recent&page=1&per_page=30
 */

class GetJson extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder result = new StringBuilder();
        HttpsURLConnection httpscon = null;
        try {
            //curl -H "Authorization: Bearer a62b88ea291c0d0e5b9295fdb8930936f945027bb84ff747ef6b89f8a9cd4da1" https://api.dribbble.com/v1/shots?sort=recent&page=1&per_page=30
            URL url = new URL("https://api.dribbble.com/v1/shots?sort=recent&page=1&per_page=30");
            httpscon = (HttpsURLConnection) url.openConnection();
            httpscon.setRequestMethod("GET");
            httpscon.setRequestProperty("Authorization", "Bearer a62b88ea291c0d0e5b9295fdb8930936f945027bb84ff747ef6b89f8a9cd4da1");

            InputStream in = new BufferedInputStream(httpscon.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}

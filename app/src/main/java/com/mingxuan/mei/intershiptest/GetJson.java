package com.mingxuan.mei.intershiptest;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Mingxuan on 10/31/2017.
 * Retrieve Json from dribbble using HttpsURLConnection.
 * cURL command:
 * curl -H "Authorization: Bearer a62b88ea291c0d0e5b9295fdb8930936f945027bb84ff747ef6b89f8a9cd4da1" https://api.dribbble.com/v1/shots?sort=recent&page=1&per_page=30
 */

class GetJson extends AsyncTask<Void, Void, String[]> {
    private String[] teasers;

    @SuppressLint("StaticFieldLeak")
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter adapter;

    GetJson(RecyclerViewAdapter adapter, SwipeRefreshLayout swipeRefreshLayout) {
        this.adapter = adapter;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);

        adapter.updateArray(strings);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }



    @Override
    protected String[] doInBackground(Void... voids) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //String contents = getJson.execute().get();
            JSONArray contentsJSON = new JSONArray(result.toString());
            teasers = new String[contentsJSON.length()];

            for (int i = 0; i < contentsJSON.length(); i++) {
                JSONObject entry = contentsJSON.getJSONObject(i);
                teasers[i] = entry.getJSONObject("images").getString("teaser");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return teasers;
    }
}
//    /**
//     * Created by Mingxuan on 10/31/2017.
//     * Read the JSONArray retrieved by GetJson.
//     * Select teaser from images of each entry.
//     */
//    Thread jsonThread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            GetJson getJson = new GetJson();
//            try {
//                //String contents = getJson.execute().get();
//                JSONArray contentsJSON = new JSONArray(result);
//                teasers = new String[contentsJSON.length()];
//
//                for (int i = 0; i < contentsJSON.length(); i++) {
//                    JSONObject entry = contentsJSON.getJSONObject(i);
//                    teasers[i] = entry.getJSONObject("images").getString("teaser");
//                }
//
//                //Test the retrieved URLs.
//                // Log.d("testTeasers ", Arrays.toString(teasers));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    });


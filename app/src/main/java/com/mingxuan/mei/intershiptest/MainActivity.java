package com.mingxuan.mei.intershiptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String[] teasers = null;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonThread.start();

        while(jsonThread.getState()!=Thread.State.TERMINATED){
            //Do nothing
        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(teasers, this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Created by Mingxuan on 10/31/2017.
     * Read the JSONArray retrieved by GetJson.
     * Select teaser from images of each entry.
     */
    Thread jsonThread = new Thread(new Runnable() {
        @Override
        public void run() {
            GetJson getJson = new GetJson();
            try {
                String contents = getJson.execute().get();
                JSONArray contentsJSON = new JSONArray(contents);
                teasers = new String[contentsJSON.length()];

                for (int i = 0; i < contentsJSON.length(); i++) {
                    JSONObject entry = contentsJSON.getJSONObject(i);
                    teasers[i] = entry.getJSONObject("images").getString("teaser");
                }

                //Test the URLs retrieved.
                // Log.d("testTeasers ", Arrays.toString(teasers));

            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }
        }
    });
}

package com.mingxuan.mei.intershiptest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    // Initialized with empty array rather than null, since RecyclerViewAdapter.getItemCount needs to access its length.
    private String[] teasers = {};

    private SwipeRefreshLayout swipeRefreshLayout; // The layout to implement "pull down to refresh"

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isRefresh = false;//if it's refreshing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(teasers, this);

        // fetch the links and update them to the adapter
        new GetJson(adapter, swipeRefreshLayout).execute();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    // fetch the links and update them to the adapter
                    new GetJson(adapter, swipeRefreshLayout).execute();
                    isRefresh = false;
                }
            }, 3000);
        }
    }

}

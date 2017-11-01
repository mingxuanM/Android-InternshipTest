package com.mingxuan.mei.intershiptest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conti on 10/31/2017.
 * The RecyclerViewAdapter to bind images with imageViews and inflate them.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);

    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private String[] teasers;
    private Context context;

    public RecyclerViewAdapter(String[] teasers, Context context) {
        this.teasers = teasers;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imageView = inflater.inflate(R.layout.image, parent, false);
        return new RecyclerViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Picasso.with(context).load(teasers[position]).into(holder.imageView);
        // holder.imageView.setImageResource(teasers.get(position));
    }

    @Override
    public int getItemCount() {
        return teasers.length;
    }

    public void updateArray(String[] teasers) {
        this.teasers = teasers;
    }
}

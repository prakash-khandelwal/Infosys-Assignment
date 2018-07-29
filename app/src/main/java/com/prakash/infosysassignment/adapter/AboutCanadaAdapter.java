package com.prakash.infosysassignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prakash.infosysassignment.R;
import com.prakash.infosysassignment.model.AboutCanadaObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/* Adapter for About Canada data population */

public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.ViewHolder> {

    private Context ctx;
    private List<AboutCanadaObject> rows;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatImageView iv_imageView;
        public AppCompatTextView tv_headingView, tv_detailView;

        public ViewHolder(View view) {
            super(view);

            iv_imageView = view.findViewById(R.id.imageView);
            tv_headingView = view.findViewById(R.id.headingView);
            tv_detailView = view.findViewById(R.id.detailView);
        }
    }

    public AboutCanadaAdapter(Context ctx, List<AboutCanadaObject> rows) {
        this.ctx = ctx;
        this.rows = rows;
    }

    @NonNull
    @Override
    public AboutCanadaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.about_canada_list_item, parent, false);
        return new AboutCanadaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutCanadaAdapter.ViewHolder holder, int position) {
        AboutCanadaObject acObject = rows.get(position);
        holder.tv_headingView.setText(acObject.getTitle());
        holder.tv_detailView.setText(acObject.getDescription());
        Picasso.with(ctx).load(acObject.getImageHref()).placeholder(R.mipmap.place_holder).into(holder.iv_imageView);
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}

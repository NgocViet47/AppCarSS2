package com.example.mypc.appcarss2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mypc.appcarss2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MyPC on 5/9/2017.
 */

public class ImgITPRCVAdapter extends RecyclerView.Adapter<ImgITPRCVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> listImgItemPost;

    public ImgITPRCVAdapter(Context context, ArrayList<String> listImgItemPost) {
        this.context = context;
        this.listImgItemPost = listImgItemPost;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.img_item_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imgPost = listImgItemPost.get(position);
        Picasso.with(context).load(imgPost)
                .fit()
                .centerCrop()
                .noFade()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        int count = (listImgItemPost != null) ? listImgItemPost.size() : 0;
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewItemPost);
        }
    }
}

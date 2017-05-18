package com.example.mypc.appcarss2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.object.ImgPost;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MyPC on 5/3/2017.
 */

public class ImgRCVAdapter extends RecyclerView.Adapter<ImgRCVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ImgPost> listImgPost;

    public ImgRCVAdapter(Context c, ArrayList<ImgPost> listItemImg) {
        this.context = c;
        this.listImgPost = listItemImg;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.img_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImgPost imgPost = listImgPost.get(position);
        Picasso.with(context).load(imgPost.getUri())
                .placeholder(R.color.colorPrimary)
                .fit()
                .centerCrop()
                .noFade()
                .tag(context)
                .into(holder.img);
        if (listImgPost.get(position).isChecked()==false){
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        int count = (listImgPost != null) ? listImgPost.size() : 0;
        return count;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imageViewItemsHome);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //loadFalseChecked();
            if (checkBox.isChecked() == true) {
                checkBox.setChecked(false);
                listImgPost.get(getAdapterPosition()).setChecked(false);
            } else {
                checkBox.setChecked(true);
                listImgPost.get(getAdapterPosition()).setChecked(true);
            }
        }

        private void loadFalseChecked() {
            for(int i =0 ;i<listImgPost.size();i++){
                listImgPost.get(i).setChecked(false);
            }
        }
    }
}

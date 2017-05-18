package com.example.mypc.appcarss2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.activity.main.ItemPostActivity;
import com.example.mypc.appcarss2.object.City;
import com.example.mypc.appcarss2.object.ItempostLoadKey;
import com.example.mypc.appcarss2.singleton.DataListSGT;
import com.example.mypc.appcarss2.utils.BundleExtras;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MyPC on 5/4/2017.
 */

public class ItemRCVAdapter extends RecyclerView.Adapter<ItemRCVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItempostLoadKey> listItemPost;

    public ItemRCVAdapter(Context context, ArrayList<ItempostLoadKey> listItemPost) {
        this.context = context;
        this.listItemPost = listItemPost;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItempostLoadKey itemPost = listItemPost.get(position);
        holder.txtTitleItem.setText(itemPost.getTitle());
        holder.txtPriceItem.setText(String.valueOf(itemPost.getPrice()));
        holder.txtCityItem.setText(loadNameCity(DataListSGT.getInstance().getCityArrayList(),itemPost.getIdCity()));
        Picasso.with(context).load(itemPost.getLinkFirstImage()).into(holder.imgItem);
        holder.txtTime.setText(itemPost.getDateTime());
        holder.txtMaker.setText(DataListSGT.getInstance().getMakerArrayList().get(itemPost.getIdMaker()).getNameMaker());
    }


    @Override
    public int getItemCount() {
        int count = (listItemPost != null) ? listItemPost.size() : 0;
        return count;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgItem;
        public TextView txtTitleItem,txtPriceItem,txtCityItem,txtTime,txtMaker;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItem = (ImageView) itemView.findViewById(R.id.imgItem);
            txtTitleItem = (TextView) itemView.findViewById(R.id.txtTitleItem);
            txtPriceItem = (TextView) itemView.findViewById(R.id.txtPrice);
            txtCityItem = (TextView) itemView.findViewById(R.id.txtCity);
            txtTime = (TextView) itemView.findViewById(R.id.txtTimeCommentITP);
            txtMaker = (TextView) itemView.findViewById(R.id.txtMaKerItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ItemPostActivity.class);
            intent.putExtra(BundleExtras.POSITIONITEM,getAdapterPosition());
            context.startActivity(intent);
        }
    }

    private String loadNameCity(ArrayList<City> arrayList, int id) {
        String a = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (id == i) {
                a = arrayList.get(i).getNameCity();
            }
        }
        return a;
    }
}

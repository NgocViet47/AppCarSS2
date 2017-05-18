package com.example.mypc.appcarss2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.object.UserCommentPost;

import java.util.ArrayList;

/**
 * Created by MyPC on 5/10/2017.
 */

public class CommentItemPostAdapter extends RecyclerView.Adapter<CommentItemPostAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserCommentPost> listCommentItemPost;

    public CommentItemPostAdapter(Context context, ArrayList<UserCommentPost> listCommentItemPost) {
        this.context = context;
        this.listCommentItemPost = listCommentItemPost;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_comment_listview, parent, false);
        return new CommentItemPostAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCommentPost userCommentPost = listCommentItemPost.get(position);
        holder.txtFullName.setText(userCommentPost.getFullName());
        holder.txtComment.setText(userCommentPost.getComment());
        holder.txtTime.setText(userCommentPost.getTime());
    }

    @Override
    public int getItemCount() {
        int count = (listCommentItemPost != null) ? listCommentItemPost.size() : 0;
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFullName, txtComment, txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            txtFullName = (TextView) itemView.findViewById(R.id.txtFullnameCommentITP);
            txtComment = (TextView) itemView.findViewById(R.id.txtCommentITP);
            txtTime = (TextView) itemView.findViewById(R.id.txtTimeCommentITP);
        }
    }
}

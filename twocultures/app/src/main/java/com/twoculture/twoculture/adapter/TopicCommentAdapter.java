package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin Song on 12/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class TopicCommentAdapter extends RecyclerView.Adapter<TopicCommentAdapter.CommentViewHolder> {
    private Context mContext;

    private List<Comment> mComments = new ArrayList<>();
    public TopicCommentAdapter(Context context){
        mContext = context;
    }

    public void setData(List<Comment> comments){
        mComments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_topic_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.textContent.setText(comment.text);
        holder.textTime.setText(comment.created_at);
        holder.textName.setText(comment.user.user_name);
        Picasso.with(mContext).load(comment.user.header_image).placeholder(R.drawable.default_gravatar).config(Bitmap.Config.RGB_565).into(holder.circleImageView);
    }



    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.civ_gravatar)
        ImageView circleImageView;

        @BindView(R.id.tv_name)
        TextView textName;

        @BindView(R.id.tv_time)
        TextView textTime;

        @BindView(R.id.tv_content)
        TextView textContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}

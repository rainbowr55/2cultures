package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.models.Comment;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder{

        public CommentViewHolder(View itemView) {
            super(itemView);
        }

    }
}

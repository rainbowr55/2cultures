package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.TopicItem;
import com.twoculture.twoculture.ui.TopicCommentsActivity;
import com.twoculture.twoculture.ui.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songxingchao on 27/09/2016.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ItemViewHolder> {
    private Context mContext;
    private List<TopicItem> mTopics = new ArrayList<>();

    public TopicAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(List<TopicItem> topics) {
        mTopics.addAll(topics);
        this.notifyDataSetChanged();
    }

    public void resetData() {
        mTopics.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_topic, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final TopicItem topicItem = mTopics.get(position);
        holder.tv_author_name.setText(topicItem.author.name);
        holder.tv_topic.setText(topicItem.topic.topic_title);
        holder.tv_comment.setText(topicItem.topic.comment_num+"");
        holder.tv_like.setText(topicItem.topic.like_num+"");
        Picasso.with(mContext).load(topicItem.author.user_header_image).placeholder(R.drawable.default_gravatar).config(Bitmap.Config.RGB_565).into(holder.iv_author_icon);
        Picasso.with(mContext).load(topicItem.topic_photos.get(0).url).config(Bitmap.Config.RGB_565).placeholder(R.drawable.default_image).fit().centerCrop().into(holder.iv_topic_icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TopicDetailActivity.class);
                Gson gson = new Gson();
                String topicItemString = gson.toJson(topicItem);
                intent.putExtra(TopicDetailActivity.TOPIC_ITEM, topicItemString);
                mContext.startActivity(intent);
            }
        });

        holder.tv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TopicCommentsActivity.class);
                intent.putExtra(TopicCommentsActivity.TOPICID,topicItem.topic.topic_id);
                mContext.startActivity(intent);
            }
        });

        holder.iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.iv_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return mTopics.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_topic_icon)
        ImageView iv_topic_icon;
        @BindView(R.id.tv_author_name)
        TextView tv_author_name;
        @BindView(R.id.tv_topic)
        TextView tv_topic;
        @BindView(R.id.iv_author_icon)
        ImageView iv_author_icon;

        @BindView(R.id.ll_comment)
        LinearLayout ll_comment;

        @BindView(R.id.tv_comment)
        TextView tv_comment;

        @BindView(R.id.tv_like)
        TextView tv_like;

        @BindView(R.id.iv_share)
        ImageView iv_share;

        @BindView(R.id.iv_favourite)
        ImageView iv_favourite;



        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}

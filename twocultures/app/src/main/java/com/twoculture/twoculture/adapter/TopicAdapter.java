package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.TopicItem;
import com.twoculture.twoculture.ui.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

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
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_topic, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final TopicItem topicItem = mTopics.get(position);
        holder.tv_author_name.setText(topicItem.author.name);
        holder.tv_topic.setText(topicItem.topic.topic_title);
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
    }


    @Override
    public int getItemCount() {
        return mTopics.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_topic_icon;
        private TextView tv_author_name;
        private TextView tv_topic;
        private ImageView iv_author_icon;


        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_topic_icon = (ImageView) itemView.findViewById(R.id.iv_topic_icon);
            tv_author_name = (TextView) itemView.findViewById(R.id.tv_author_name);
            tv_topic = (TextView) itemView.findViewById(R.id.tv_topic);
            iv_author_icon = (ImageView) itemView.findViewById(R.id.iv_author_icon);
        }

    }
}

package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twoculture.base.widget.RoundedImageView;
import com.twoculture.base.widget.RoundedTransformationBuilder;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.AtNotification;
import com.twoculture.twoculture.ui.EventDetailActivity;
import com.twoculture.twoculture.ui.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rainbow on 17/1/2.
 */

public class AtMessageAdapter extends RecyclerView.Adapter<AtMessageAdapter.AtMessageViewHolder> {

    private Context mContext;

    private List<AtNotification> atNotifications = new ArrayList<>();

    public AtMessageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addData(List<AtNotification> atNotifications) {
        this.atNotifications.addAll(atNotifications);
        this.notifyDataSetChanged();

    }

    @Override
    public AtMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_at_notification, parent, false);
        return new AtMessageViewHolder(item);
    }

    @Override
    public void onBindViewHolder(AtMessageViewHolder holder, int position) {
        AtNotification atNotification = atNotifications.get(position);
        holder.bindTo(atNotification);
        holder.rlAtMessageContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (atNotification.notification_attribute.notification_type.equalsIgnoreCase("TopicComment")) {
                    Intent intent = new Intent(mContext, TopicDetailActivity.class);
                    intent.putExtra(TopicDetailActivity.TOPIC_ID, atNotification.notification_attribute.notification_table.parent_id);
                    mContext.startActivity(intent);
                    atNotification.notification_attribute.is_read = true;
                    holder.readStatus.setVisibility(View.GONE);
                } else if (atNotification.notification_attribute.notification_type.equalsIgnoreCase("EventComment")) {
                    Intent intent = new Intent(mContext, EventDetailActivity.class);
                    intent.putExtra(EventDetailActivity.EVENT_ID, atNotification.notification_attribute.notification_table.parent_id);
                    mContext.startActivity(intent);
                    holder.readStatus.setVisibility(View.GONE);
                    atNotification.notification_attribute.is_read = true;
                }
            }
        });
        if (atNotification != null && atNotification.from_user != null) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.WHITE)
                    .borderWidthDp(1)
                    .cornerRadiusDp(40)
                    .oval(false)
                    .build();

            Picasso.with(mContext)
                    .load(atNotification.from_user.from_user_header_image)
                    .fit()
                    .transform(transformation)
                    .placeholder(R.drawable.default_gravatar).fit().centerCrop().into(holder.ivUserHead);

        }

    }

    @Override
    public int getItemCount() {
        return atNotifications == null ? 0 : atNotifications.size();
    }

    public static class AtMessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_at_message_content)
        RelativeLayout rlAtMessageContent;
        @BindView(R.id.ll_user_head)
        LinearLayout llUserHead;
        @BindView(R.id.read_status)
        View readStatus;
        @BindView(R.id.iv_user_head)
        RoundedImageView ivUserHead;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_at_message_time)
        TextView tvAtMessageTime;
        @BindView(R.id.tv_message_content)
        TextView tvMessageContent;
        @BindView(R.id.tv_message_type)
        TextView tvMessageType;

        public AtMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(AtNotification atNotification) {

            if (atNotification.notification_attribute.is_read) {
                readStatus.setVisibility(View.GONE);
            } else {
                readStatus.setVisibility(View.VISIBLE);
            }
            tvUserName.setText(atNotification.from_user.from_user_name);
            tvAtMessageTime.setText(atNotification.notification_attribute.created_at);
            tvMessageContent.setText(atNotification.notification_attribute.notification_table.title);
            tvMessageType.setText(atNotification.notification_attribute.notification_type);
        }
    }
}

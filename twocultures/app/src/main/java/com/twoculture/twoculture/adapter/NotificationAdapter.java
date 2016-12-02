package com.twoculture.twoculture.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.SystemMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liangcaihong on 2016/12/2.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<SystemMessage> notifications;

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(item);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bindTo(notifications.get(position));
    }

    public void addDatas(List<SystemMessage> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notifications == null ? 0 : notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_notification_title)
        TextView tvNotificationTitle;
        @BindView(R.id.tv_notification_time)
        TextView tvNotificationTime;
        @BindView(R.id.tv_notification_description)
        TextView tvNotificationDescription;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(SystemMessage notification) {
            if (notification.notification != null) {
                tvNotificationTitle.setText(notification.notification.title);
                tvNotificationDescription.setText(notification.notification.description);
            }
            if (notification.message != null) {
                tvNotificationTime.setText(notification.message.created_at);
            }

        }
    }
}

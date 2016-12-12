package com.twoculture.twoculture.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.EventInvite;
import com.twoculture.twoculture.ui.interfaces.ViewMoreClickCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liangcaihong on 2016/12/2.
 */

public class EventInvitationAdapter extends RecyclerView.Adapter<EventInvitationAdapter.NotificationViewHolder> {

    private List<EventInvite> eventInvites;

    public ViewMoreClickCallback viewMoreClickCallback;


    public EventInvitationAdapter(ViewMoreClickCallback viewMoreClickCallback) {
        this.viewMoreClickCallback = viewMoreClickCallback;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(item);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        holder.bindTo(eventInvites.get(position));
        holder.tvInvitationViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewMoreClickCallback != null) {
                    viewMoreClickCallback.onViewMoreClick(eventInvites.get(position));
                }
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewMoreClickCallback != null) {
                    viewMoreClickCallback.onDeleteEventInvite(eventInvites.get(position));
                }
            }
        });
    }

    public void delData(EventInvite eventInvite) {
        this.eventInvites.remove(eventInvite);
        notifyDataSetChanged();
    }

    public void addDatas(List<EventInvite> eventInvites) {
        this.eventInvites = eventInvites;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return eventInvites == null ? 0 : eventInvites.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_message_type)
        ImageView ivMessageType;
        @BindView(R.id.rl_invitation_view_more)
        RelativeLayout rlInvitationViewMore;
        @BindView(R.id.tv_invitation_view_more)
        TextView tvInvitationViewMore;
        @BindView(R.id.tv_notification_title)
        TextView tvNotificationTitle;
        @BindView(R.id.tv_notification_time)
        TextView tvNotificationTime;
        @BindView(R.id.tv_notification_description)
        TextView tvNotificationDescription;
        @BindView(R.id.iv_del)
        ImageView ivDel;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(EventInvite eventInvite) {
            ivDel.setVisibility(View.VISIBLE);
            ivMessageType.setBackgroundResource(R.mipmap.ic_invitation);
            rlInvitationViewMore.setVisibility(View.VISIBLE);
            if (eventInvite.event != null) {
                tvNotificationTitle.setText(eventInvite.event.title);
                tvNotificationDescription.setText(eventInvite.event.description);
            }
            if (eventInvite.message != null) {
                tvNotificationTime.setText(eventInvite.message.created_at);
            }


        }
    }
}

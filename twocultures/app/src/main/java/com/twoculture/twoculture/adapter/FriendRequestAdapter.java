package com.twoculture.twoculture.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.FriendRequest;
import com.twoculture.twoculture.ui.FriendRequestActivity;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rainbow on 16/11/25.
 */

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.FriendRequestViewHolder> {


    private List<FriendRequest> friendRequests;

    public FriendRequestAdapter(FriendRequestActivity.FriendRequestClickCallback friendRequestClickCallback) {
        this.friendRequestClickCallback = friendRequestClickCallback;
    }

    private FriendRequestActivity.FriendRequestClickCallback friendRequestClickCallback;

    @Override
    public FriendRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend_request, parent, false);
        return new FriendRequestViewHolder(item, friendRequestClickCallback);
    }

    @Override
    public void onBindViewHolder(FriendRequestViewHolder holder, int position) {

        holder.bindTo(friendRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return friendRequests == null ? 0 : friendRequests.size();
    }

    public void addDatas(List<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
        notifyDataSetChanged();
    }

    public void deleteItem(int messageId) {
        if (friendRequests != null && friendRequests.size() > 0) {

            Iterator it = friendRequests.iterator();

            while (it.hasNext()) {
                FriendRequest friendRequest = (FriendRequest) it.next();
                if (messageId == friendRequest.message_id) {
                    it.remove();
                }
            }
            notifyDataSetChanged();
        }
    }

    public static class FriendRequestViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.friend_name)
        TextView friendName;
        @BindView(R.id.friend_agree_btn)
        ImageView agreeBtn;

        FriendRequest friendRequest;

        public FriendRequestViewHolder(View itemView, FriendRequestActivity.FriendRequestClickCallback friendRequestClickCallback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // 绑定点击事件
            agreeBtn.setOnClickListener(v ->
                    friendRequestClickCallback.onItemClicked(friendRequest.message_id));

        }

        public void bindTo(FriendRequest friendRequest) {
            this.friendRequest = friendRequest;
            friendName.setText(friendRequest.from_user_name);

        }
    }
}

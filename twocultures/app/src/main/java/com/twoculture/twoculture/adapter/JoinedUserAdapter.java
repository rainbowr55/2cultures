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

import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.response.EventUsersListResponse;
import com.twoculture.twoculture.ui.MyProfileActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songxingchao on 27/09/2016.
 */

public class JoinedUserAdapter extends RecyclerView.Adapter<JoinedUserAdapter.ItemViewHolder> {
    private Context mContext;
    private List<EventUsersListResponse.User> mUsers = new ArrayList<>();

    public JoinedUserAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(List<EventUsersListResponse.User> users) {
        mUsers.addAll(users);
        this.notifyDataSetChanged();
    }

    public void resetData() {
        mUsers.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final EventUsersListResponse.User user = mUsers.get(position);
        holder.textName.setText(user.user_name);
        Picasso.with(mContext).load(user.header_image).placeholder(R.drawable.default_gravatar).config(Bitmap.Config.RGB_565).into(holder.imageGravatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyProfileActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_gravatar)
        ImageView imageGravatar;
        @BindView(R.id.tv_name)
        TextView textName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

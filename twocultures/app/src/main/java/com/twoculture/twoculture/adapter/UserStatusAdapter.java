package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.UserStatu;
import com.twoculture.twoculture.ui.interfaces.UserStatusClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class UserStatusAdapter extends RecyclerView.Adapter<UserStatusAdapter.MyViewHolder> {

    private List<UserStatu> status;

    private UserStatusClickListener userStatusClickListener;


    public UserStatusAdapter(Context context) {
        if (context instanceof UserStatusClickListener) {
            this.userStatusClickListener = (UserStatusClickListener) context;
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_profile_country_item, parent, false);
        return new UserStatusAdapter.MyViewHolder(viewItem);

    }

    public void addData(List<UserStatu> countries) {
        this.status = countries;
        this.notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.districtName.setText(status.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userStatusClickListener != null) {
                    userStatusClickListener.onUserStatusClick(status.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return status != null ? status.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.district_name_text)
        TextView districtName;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}

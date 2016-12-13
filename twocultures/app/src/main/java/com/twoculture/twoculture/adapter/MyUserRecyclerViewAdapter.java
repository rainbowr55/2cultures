package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twoculture.base.widget.RoundedTransformationBuilder;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.User;
import com.twoculture.base.widget.RoundedImageView;
import com.twoculture.twoculture.ui.interfaces.UserClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private List<User> mValues;
    private final UserClickListener mListener;
    private Context mContext;

    public MyUserRecyclerViewAdapter(Context context, UserClickListener userClickListener) {
        this.mContext = context;
        mListener = userClickListener;
    }

    public void addDatas(List<User> items) {
        this.mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        User user = mValues.get(position);
        holder.llUserItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserClick(user);
                }
            }
        });
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.WHITE)
                .borderWidthDp(1)
                .cornerRadiusDp(40)
                .oval(false)
                .build();

        Picasso.with(mContext)
                .load(user.header_image)
                .fit()
                .transform(transformation)
                .placeholder(R.drawable.default_gravatar).fit().centerCrop().into(holder.ivUserHeadIcon);
        holder.tvUserName.setText(user.user_name);
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_user_item)
        LinearLayout llUserItem;
        @BindView(R.id.iv_user_head_icon)
        RoundedImageView ivUserHeadIcon;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.models.TopicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songxingchao on 3/10/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Context mContext;
    private List<EventItem> mEventsList = new ArrayList<>();

    public void addData(List<EventItem> events){
        mEventsList.addAll(events);
        this.notifyDataSetChanged();
    }

    public void resetData(){
        mEventsList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext)
                .inflate(R.layout.item_main_event,parent,false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        EventItem eventItem = mEventsList.get(position);

    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}

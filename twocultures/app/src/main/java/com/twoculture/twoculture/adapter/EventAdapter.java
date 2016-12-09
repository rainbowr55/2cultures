package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.response.BaseResponse;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.ui.EventDetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 3/10/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Context mContext;
    private List<EventItem> mEventsList = new ArrayList<>();

    public EventAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(List<EventItem> events) {
        mEventsList.addAll(events);
        this.notifyDataSetChanged();
    }

    public void resetData() {
        mEventsList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext)
                .inflate(R.layout.item_main_event, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final EventItem eventItem = mEventsList.get(position);
        holder.tv_title.setText(eventItem.title);

        holder.tv_joined_number.setText(eventItem.join_num+"");
        Picasso.with(mContext).load(eventItem.event_bg).placeholder(R.drawable.default_image).fit().centerCrop().into(holder.iv_event_bg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                Gson gson = new Gson();
                String eventString = gson.toJson(eventItem);
                intent.putExtra(EventDetailActivity.EVENT_DATA, eventString);
                mContext.startActivity(intent);
            }
        });
        holder.iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(eventItem.is_favorite){
                RxClient.getInstance().getTopicService().unfavouriteTopic(eventItem.event_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<BaseResponse>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BaseResponse baseResponse) {
                                eventItem.join_num -= 1;
                                holder.tv_joined_number.setText(eventItem.join_num+"");
                                Toast.makeText(mContext,baseResponse.msg,Toast.LENGTH_SHORT).show();
                                eventItem.is_favorite = false;
                            }
                        });
             }else{
                 RxClient.getInstance().favouriteTopic(eventItem.event_id)
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribeOn(Schedulers.io())
                         .subscribe(new Observer<BaseResponse>() {
                             @Override
                             public void onCompleted() {

                             }

                             @Override
                             public void onError(Throwable e) {

                             }

                             @Override
                             public void onNext(BaseResponse baseResponse) {
                                 eventItem.join_num +=1;
                                 holder.tv_joined_number.setText(eventItem.join_num+"");
                                 Toast.makeText(mContext,baseResponse.msg,Toast.LENGTH_SHORT).show();
                                 eventItem.is_favorite = true;
                             }
                         });
             }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_event_bg;
        TextView tv_joined_number;
        ImageView iv_like;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_event_bg = (ImageView) itemView.findViewById(R.id.iv_event_bg);
            tv_joined_number = (TextView) itemView.findViewById(R.id.tv_joined_number);
            iv_like = (ImageView) itemView.findViewById(R.id.iv_like);
        }
    }
}

package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.network.RxClient;

import java.util.List;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 1/10/2016.
 */

public class EventsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe_refresh_widget;
    private RecyclerView rv_events;
    private Subscription subscription;
    private int mPageIndex = 0;
    private static final int PAGESIZE = 10;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        initView(rootView);
        getDataFromServer();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView(View rootView) {
        rv_events = (RecyclerView) rootView.findViewById(R.id.rv_events);
        swipe_refresh_widget = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
    }

    private void getDataFromServer() {
        subscription = RxClient.getInstance()
                .getAllEvents(mPageIndex, PAGESIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<EventItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<EventItem> eventItems) {

                    }
                });
    }


        @Override
        public void onRefresh () {

        }

}

package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.EventAdapter;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.presenter.EventsPresenter;
import com.twoculture.twoculture.presenter.IEventsPresenter;
import com.twoculture.twoculture.presenter.ITopicPresenter;
import com.twoculture.twoculture.views.IEventsView;

import java.util.List;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 1/10/2016.
 */

public class EventsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IEventsView {

    private SwipeRefreshLayout swipe_refresh_widget;
    private RecyclerView rv_events;
    private Subscription subscription;
    private int mPageIndex = 0;
    private static final int PAGESIZE = 10;
    private EventAdapter mEventAdapter;
    private IEventsPresenter mEventsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        mEventsPresenter = new EventsPresenter(this);
        initView(rootView);
        mEventsPresenter.getDataFromServer(mPageIndex, PAGESIZE);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView(View rootView) {
        rv_events = (RecyclerView) rootView.findViewById(R.id.rv_events);
        rv_events.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        swipe_refresh_widget = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
        mEventAdapter = new EventAdapter(getActivity());
        rv_events.setAdapter(mEventAdapter);
    }


    @Override
    public void onRefresh() {
        mPageIndex = 0;
        mEventsPresenter.getDataFromServer(mPageIndex, PAGESIZE);
    }

    @Override
    public void showRefreshing(boolean refreshing) {
        swipe_refresh_widget.setRefreshing(refreshing);
    }

    @Override
    public void addTopics(List<EventItem> eventItems) {
        mEventAdapter.addData(eventItems);
    }
}

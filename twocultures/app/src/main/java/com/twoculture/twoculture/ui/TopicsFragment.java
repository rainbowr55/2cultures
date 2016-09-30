package com.twoculture.twoculture.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.TopicAdapter;
import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.network.RxClient;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 27/09/2016.
 */

public class TopicsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView rv_topics;
    private SwipeRefreshLayout swipe_refresh_widget;
    private Subscription subscription;
    private TopicAdapter mTopicsAdapter;
    private boolean mLoading = false;
    private int mPageIndex = 0;
    private static final int PAGESIZE = 10;
    public TopicsFragment(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topics,container,false);
        initView(rootView);
        getDataFromServer();
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView(View rootView){
        rv_topics = (RecyclerView) rootView.findViewById(R.id.rv_topics);
        swipe_refresh_widget = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
        mTopicsAdapter = new TopicAdapter(getActivity());
        rv_topics.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_topics.setAdapter(mTopicsAdapter);
        swipe_refresh_widget.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        swipe_refresh_widget.setOnRefreshListener(this);
        rv_topics.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem%10 ==0) {
                    mLoading = true;
                    //show the footer
                    //loadmoredata
                    mPageIndex ++;
                    getDataFromServer();
                }

            }
        });
    }
    private void getDataFromServer(){
        subscription =  RxClient.getInstance().getAllTopics(mPageIndex,PAGESIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllTopics>() {
                    @Override
                    public void onCompleted() {
                        swipe_refresh_widget.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        swipe_refresh_widget.setRefreshing(false);
                    }

                    @Override
                    public void onNext(AllTopics allTopics) {
                        swipe_refresh_widget.setRefreshing(false);
                        Log.d(TopicsFragment.class.getName(),"onNext");
                        if(mPageIndex==0){
                            mTopicsAdapter.resetData();
                        }
                        mTopicsAdapter.addData(allTopics.topics);
                    }
                });
    }

    @Override
    public void onRefresh() {
        mPageIndex = 0;
        getDataFromServer();
    }




}

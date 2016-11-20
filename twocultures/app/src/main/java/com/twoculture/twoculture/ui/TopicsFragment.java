package com.twoculture.twoculture.ui;

import android.graphics.Color;
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
import com.twoculture.twoculture.adapter.TopicAdapter;
import com.twoculture.twoculture.models.TopicItem;
import com.twoculture.twoculture.presenter.TopicsPresenter;
import com.twoculture.twoculture.views.ITopicsView;

import java.util.List;

import rx.Subscription;

/**
 * Created by songxingchao on 27/09/2016.
 */

public class TopicsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ITopicsView {


    private static final int PAGESIZE = 10;
    private RecyclerView rv_topics;
    private SwipeRefreshLayout swipe_refresh_widget;
    private Subscription subscription;
    private TopicAdapter mTopicsAdapter;
    private boolean mLoading = false;
    private int mPageIndex = 0;
    private TopicsPresenter mTopicsPresenter;

    public TopicsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);
        mTopicsPresenter = new TopicsPresenter(this);
        initView(rootView);
        mTopicsPresenter.getDataFromServer(mPageIndex, PAGESIZE);
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView(View rootView) {
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
                if (lastVisibleItem % 10 == 0) {
                    mLoading = true;
                    //show the footer
                    //loadmoredata
                    mPageIndex++;
                    mTopicsPresenter.getDataFromServer(mPageIndex, PAGESIZE);
                }

            }
        });
    }


    @Override
    public void onRefresh() {
        mPageIndex = 0;
        mTopicsPresenter.getDataFromServer(mPageIndex, PAGESIZE);
    }


    @Override
    public void showRefreshing(boolean refreshing) {
        swipe_refresh_widget.setRefreshing(refreshing);
    }

    @Override
    public void addTopics(List<TopicItem> topics) {
        if (mPageIndex == 0) {
            mTopicsAdapter.resetData();
        }
        mTopicsAdapter.addData(topics);
    }
}

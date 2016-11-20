package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.models.AllTopics;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.ui.TopicsFragment;
import com.twoculture.twoculture.views.ITopicsView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 28/10/2016.
 */

public class TopicsPresenter implements ITopicPresenter {

    private ITopicsView mTopicsView;

    public TopicsPresenter(ITopicsView topicsView) {
        mTopicsView = topicsView;
    }

    @Override
    public void getDataFromServer(int pageIndex, int pageSize) {
        RxClient.getInstance().getAllTopics(pageIndex, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllTopics>() {
                    @Override
                    public void onCompleted() {

                        mTopicsView.showRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mTopicsView.showRefreshing(false);
                    }

                    @Override
                    public void onNext(AllTopics allTopics) {
                        Log.d(TopicsFragment.class.getName(), "onNext");
                        mTopicsView.showRefreshing(false);
                        mTopicsView.addTopics(allTopics.topics);

                    }
                });
    }
}

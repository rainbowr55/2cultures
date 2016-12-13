package com.twoculture.twoculture.presenter;

import com.twoculture.twoculture.models.Comment;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.ITopicCommentsView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kevin Song on 13/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class TopicCommentsPresenter implements BasePresenter{
    private ITopicCommentsView mView;
    private Subscription mSubscription;

    public TopicCommentsPresenter(ITopicCommentsView view) {
        mView = view;
    }


    public void loadComments(int mTopicId,int pageIndex,int pageNumber) {
        mSubscription = RxClient.getInstance().getTopicService().getComments(AppConstants.TOKEN,mTopicId,pageIndex,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        mView.refreshData(comments);
                    }
                });
    }

    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }
}

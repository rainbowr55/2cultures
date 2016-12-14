package com.twoculture.twoculture.presenter;

import com.twoculture.twoculture.models.Comment;
import com.twoculture.twoculture.models.response.PostCommentResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.ITopicCommentsView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Kevin Song on 13/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class TopicCommentsPresenter implements BasePresenter {


    private ITopicCommentsView mView;
    private CompositeSubscription mSubscriptions;

    public TopicCommentsPresenter(ITopicCommentsView view) {
        mView = view;
        mSubscriptions = new CompositeSubscription();
    }


    public void loadComments(int mTopicId, int pageIndex, int pageNumber) {
        Subscription subscription = RxClient.getInstance().getTopicService().getComments(AppConstants.TOKEN, mTopicId, pageIndex, pageNumber)
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
        mSubscriptions.add(subscription);
    }

    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }

    public void postComment(int topicId, String commentContent) {
        Subscription subscription = RxClient.getInstance().getTopicService().postComment(AppConstants.TOKEN, topicId, commentContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostCommentResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.postResult(false,e.getMessage());
                    }

                    @Override
                    public void onNext(PostCommentResponse postCommentResponse) {
                            if(postCommentResponse.status==200){
                                mView.postResult(true,"");
                            }else{
                                mView.postResult(false,postCommentResponse.msg);
                            }
                    }
                });
        mSubscriptions.add(subscription);
    }
}

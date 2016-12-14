package com.twoculture.twoculture.presenter;

import com.twoculture.twoculture.models.response.BaseResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.ITopicDetailView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Kevin Song on 14/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class TopicDetailPresenter implements BasePresenter {

    private CompositeSubscription mSubscriptions;
    private ITopicDetailView mView;
    public TopicDetailPresenter(ITopicDetailView view){
        mSubscriptions = new CompositeSubscription();
        mView = view;
    }


    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {
        mSubscriptions.clear();
    }

    public void likeTopic(int topicId) {
        Subscription subscription = RxClient.getInstance().getTopicService().likeTopic(AppConstants.TOKEN,topicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setLikeResult(false,e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.status == 0){
                            mView.setLikeResult(true,baseResponse.msg);
                        }else{
                            mView.setLikeResult(false,baseResponse.msg);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void unlikeTopic(int topicId) {
        Subscription subscription = RxClient.getInstance().getTopicService().unlikeTopic(AppConstants.TOKEN,topicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setUnikeResult(false,e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if(baseResponse.status == 2){
                            mView.setUnikeResult(true,baseResponse.msg);
                        }else{
                            mView.setUnikeResult(false,baseResponse.msg);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }
}

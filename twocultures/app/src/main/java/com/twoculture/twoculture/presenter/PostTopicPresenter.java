package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.models.response.PostTopicResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kevin Song on 9/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class PostTopicPresenter implements IPostTopicPresenter{

    private static final String TAG = PostTopicPresenter.class.getSimpleName();
    private Subscription mSubscription;
    @Override
    public void startPresenter() {

    }

    @Override
    public void stopPresenter() {
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
    }


    @Override
    public void postTopic(String header, String content, String pictures) {
        mSubscription =  RxClient.getInstance().getTopicService().postTopic(AppConstants.TOKEN,header,content,1,false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostTopicResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(PostTopicResponse postTopicResponse) {

                    }
                });
    }

}

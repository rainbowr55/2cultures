package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.models.EventUsersListResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IJoinedUserView;

import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kevin Song on 5/12/2016.
 * Copyright (c) 2016 Woolworths. All rights reserved.
 */

public class JoinedUserPresenter implements BasePresenter{
    public static final String TAG = JoinedUserPresenter.class.getSimpleName();
    private static final int PAGE_NUMBER = 40;
    private IJoinedUserView mJoinedUserView;
    private Subscription mSubscription;
    public JoinedUserPresenter(IJoinedUserView view){
        mJoinedUserView = view;
    }
    public void loadAllUsers(int eventId){
        mSubscription =  RxClient.getInstance().getTopicService().getJoinedUser(AppConstants.TOKEN,eventId,1,PAGE_NUMBER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EventUsersListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EventUsersListResponse eventUsersListResponse) {
                        Log.d(TAG,eventUsersListResponse.toString()+"");
                        mJoinedUserView.refreshList(eventUsersListResponse.joined_users);
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

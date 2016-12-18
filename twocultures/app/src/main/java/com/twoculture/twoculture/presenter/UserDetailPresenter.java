package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.UserService;
import com.twoculture.twoculture.models.UserDetail;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IUserDetail;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liangcaihong on 2016/12/14.
 */

public class UserDetailPresenter implements IUserDetailPresenter {

    private IUserDetail userDetailView;

    public UserDetailPresenter(IUserDetail userDetailView) {
        this.userDetailView = userDetailView;
    }

    @Override
    public void getUserDetail(String userId) {
        userDetailView.onLoadingShow();
        RxClient.getInstance().getService(UserService.class).getUserDetail(AppConstants.TOKEN,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        userDetailView.setMessage(e.getMessage());
                        userDetailView.onLoadFailed();
                    }

                    @Override
                    public void onNext(UserDetail users) {
                        userDetailView.onLoadSuccess(users);

                    }
                });
    }


}

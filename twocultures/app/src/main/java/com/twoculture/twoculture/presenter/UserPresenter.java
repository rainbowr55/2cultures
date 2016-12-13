package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.UserService;
import com.twoculture.twoculture.models.User;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IUserView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liangcaihong on 2016/12/13.
 */

public class UserPresenter implements IUserPresenter {

    private IUserView userView;

    public UserPresenter(IUserView userView) {
        this.userView = userView;
    }

    @Override
    public void searchUser(String searchName) {
        userView.onLoadingShow();
        RxClient.getInstance().getService(UserService.class).getAllUser(AppConstants.TOKEN, searchName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        userView.setMessage(e.getMessage());
                        userView.onLoadFailed();
                    }

                    @Override
                    public void onNext(List<User> users) {
                        userView.onLoadSuccess(users);

                    }
                });
    }
}

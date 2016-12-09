package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.LoginService;
import com.twoculture.twoculture.models.UserProfile;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IUserProfileView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liangcaihong on 2016/12/6.
 */

public class UserProfilePresenter implements IUserProfilePresenter {

    private IUserProfileView userProfileView;

    public UserProfilePresenter(IUserProfileView userProfileView) {
        this.userProfileView = userProfileView;
    }

    @Override
    public void getUserProfile() {
        userProfileView.onLoadingShow();
        RxClient.getInstance().getService(LoginService.class).getUserProfile(AppConstants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserProfile>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        userProfileView.setMessage(e.getMessage());
                        userProfileView.onLoadFailed();
                    }

                    @Override
                    public void onNext(UserProfile userProfile) {
                        userProfileView.onLoadSuccess(userProfile);

                    }
                });
    }


}

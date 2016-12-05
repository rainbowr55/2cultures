package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.NotificationService;
import com.twoculture.twoculture.models.SystemMessage;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.INotificationView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liangcaihong on 2016/12/2.
 */

public class NotificationPresenter implements INotificationPresenter {

    private INotificationView mNotificationView;

    public NotificationPresenter(INotificationView mNotificationView) {
        this.mNotificationView = mNotificationView;
    }

    @Override
    public void getNotificationList() {
        mNotificationView.onLoadingShow();
        RxClient.getInstance().getService(NotificationService.class).getNotificationList(AppConstants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SystemMessage>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        mNotificationView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<SystemMessage> notifications) {
                        mNotificationView.onLoadSuccess(notifications);

                    }
                });
    }
}

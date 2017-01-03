package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.MessageAtService;
import com.twoculture.twoculture.models.AtNotificationList;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IAtMessageView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rainbow on 17/1/2.
 */

public class MessageAtPresenter implements IMessageAtPresenter {

    private IAtMessageView messageView;

    public MessageAtPresenter(IAtMessageView messageView) {
        this.messageView = messageView;
    }

    @Override
    public void getAtMessages(int page) {
        messageView.onLoadingShow();
        RxClient.getInstance().getService(MessageAtService.class).getAtNotifications(AppConstants.TOKEN,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AtNotificationList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        messageView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(AtNotificationList notifications) {
                        messageView.onLoadSuccess(notifications);

                    }
                });

    }
}

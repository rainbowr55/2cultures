package com.twoculture.twoculture.presenter;

import android.app.Dialog;

import com.twoculture.twoculture.adapter.FriendRequestAdapter;
import com.twoculture.twoculture.interfaces.MessageService;
import com.twoculture.twoculture.models.FriendRequest;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.ViewHelper;
import com.twoculture.twoculture.views.IFriendRequestView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rainbow on 16/11/25.
 */

public class FriendRequestPresenter implements IFriendRequestPresenter {

    private IFriendRequestView mFriendRequestView;

    public FriendRequestPresenter(IFriendRequestView mFriendRequestView) {
        this.mFriendRequestView = mFriendRequestView;
    }


    @Override
    public void getFriendRequestList(FriendRequestAdapter friendRequestAdapter) {
        final Dialog dialog = ViewHelper.show(mFriendRequestView);
        RxClient.getInstance().getService(MessageService.class).getMessageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FriendRequest>>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(List<FriendRequest> friendRequest) {
                        friendRequestAdapter.addDatas(friendRequest);
                        dialog.dismiss();
                    }
                });
    }
}


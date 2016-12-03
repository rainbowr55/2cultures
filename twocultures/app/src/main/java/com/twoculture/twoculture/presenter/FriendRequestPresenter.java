package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.MessageService;
import com.twoculture.twoculture.models.BaseResponse;
import com.twoculture.twoculture.models.FriendRequest;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IFriendRequestView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rainbow on 16/11/25.
 */

public class FriendRequestPresenter implements IFriendRequestPresenter {

    /**
     * :accepted => 1, 已接受成为朋友，也用于处理朋友申请时所传的process_result_code处理码
     * :rejected => 2, 拒绝成为朋友 也用于处理朋友申请时所传的process_result_code处理码
     * :canceled => 3, 成功取消
     * :cancel_failed => 4 取消失败
     * :time_out => 5,消息超时
     */
    public static final int FRIEND_ACCEPTED = 1;
    public static final int FRIEND_REJECTED = 2;

    public static final int FRIEND_CANCLE = 3;
    public static final int FRIEND_CANCLE_FAILED = 4;
    public static final int FRIEND_TIME_OUT = 5;


    private IFriendRequestView mFriendRequestView;

    public FriendRequestPresenter(IFriendRequestView mFriendRequestView) {
        this.mFriendRequestView = mFriendRequestView;
    }


    @Override
    public void getFriendRequestList() {
        mFriendRequestView.onLoadingShow();

        RxClient.getInstance().getService(MessageService.class).getMessageList(AppConstants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FriendRequest>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        mFriendRequestView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<FriendRequest> friendRequest) {
                        mFriendRequestView.onLoadSuccess(friendRequest);

                    }
                });
    }

    @Override
    public int processFriendRequest(int messageId, int processCode) {
        RxClient.getInstance().getService(MessageService.class).processFriendRequest(AppConstants.TOKEN, messageId, processCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        mFriendRequestView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse response) {
                        mFriendRequestView.onProcessFriendRequest(messageId,response);

                    }
                });
        return 0;
    }
}


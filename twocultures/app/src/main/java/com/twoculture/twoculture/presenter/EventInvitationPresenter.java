package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.EventInvitationService;
import com.twoculture.twoculture.models.EventInvite;
import com.twoculture.twoculture.models.response.BaseResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IEventInvitationView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rainbow on 16/12/12.
 */

public class EventInvitationPresenter implements IEventInvitationPresenter {

    private IEventInvitationView eventInvitationView;

    public EventInvitationPresenter(IEventInvitationView eventInvitationView) {
        this.eventInvitationView = eventInvitationView;
    }

    @Override
    public void getEventInvitations() {
        eventInvitationView.onLoadingShow();
        RxClient.getInstance().getService(EventInvitationService.class).getEventInvitations(AppConstants.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<EventInvite>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        eventInvitationView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<EventInvite> eventInvites) {
                        eventInvitationView.onLoadSuccess(eventInvites);

                    }
                });
    }

    public void delEventInvitation(EventInvite eventInvite) {
        if (eventInvite == null) {
            return;
        }
        eventInvitationView.onLoadingShow();
        RxClient.getInstance().getService(EventInvitationService.class).removeInvitation(AppConstants.TOKEN, eventInvite.message.message_id)
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
                        eventInvitationView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        eventInvitationView.setMessage(baseResponse.msg);
                        eventInvitationView.onDelEventInvitation(eventInvite);

                    }
                });

    }
}

package com.twoculture.twoculture.presenter;

import android.util.Log;

import com.twoculture.twoculture.interfaces.EventService;
import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.IEventDetailView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rainbow on 17/1/3.
 */

public class EventDetailPresenter implements IEventDetailPresenter {

    private IEventDetailView eventDetailView;

    public EventDetailPresenter(IEventDetailView eventDetailView) {
        this.eventDetailView = eventDetailView;
    }

    @Override
    public void getEventDetail(int eventId) {
        RxClient.getInstance().getService(EventService.class).getEventDetail(AppConstants.TOKEN,eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EventItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("test", e.getMessage());
                        eventDetailView.setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(EventItem eventItem) {
                        eventDetailView.onLoadSuccess(eventItem);

                    }
                });
    }
}

package com.twoculture.twoculture.presenter;

import com.twoculture.twoculture.models.EventItem;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.views.IEventsView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by songxingchao on 28/10/2016.
 */

public class EventsPresenter implements IEventsPresenter {

    private IEventsView mEventsView;

    public EventsPresenter(IEventsView eventsView) {
        mEventsView = eventsView;
    }

    @Override
    public void getDataFromServer(int pageIndex, int pageSize) {
        RxClient.getInstance()
                .getAllEvents(pageIndex, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<EventItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mEventsView.showRefreshing(false);
                    }

                    @Override
                    public void onNext(List<EventItem> eventItems) {
                        mEventsView.showRefreshing(false);
                        mEventsView.addTopics(eventItems);
                    }
                });
    }
}

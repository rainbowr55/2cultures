package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.EventInvitationAdapter;
import com.twoculture.twoculture.models.EventInvite;
import com.twoculture.twoculture.presenter.EventInvitationPresenter;
import com.twoculture.twoculture.ui.interfaces.ViewMoreClickCallback;
import com.twoculture.twoculture.views.IEventInvitationView;

import java.util.List;

import butterknife.BindView;

public class EventInvitationActivity extends BaseActivity implements IEventInvitationView, ViewMoreClickCallback {

    private EventInvitationPresenter eventInvitationPresenter;

    private EventInvitationAdapter eventInvitationAdapter;

    @BindView(R.id.rv_events)
    RecyclerView rvEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        eventInvitationPresenter = new EventInvitationPresenter(this);
        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvEvents.setLayoutManager(layoutManager);
        // 设置适配器
        eventInvitationAdapter = new EventInvitationAdapter(this);
        eventInvitationPresenter.getEventInvitations();
        rvEvents.setAdapter(eventInvitationAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_event_invitation;
    }

    @Override
    public void onLoadSuccess(List<EventInvite> eventInvites) {
        if (eventInvites != null && eventInvites.size() > 0) {
            eventInvitationAdapter.addDatas(eventInvites);
        } else {
            showToast(this.getString(R.string.no_data_hint));
        }
        dismissLoadding();
    }

    @Override
    public void onDelEventInvitation(EventInvite eventInvite) {
        eventInvitationAdapter.delData(eventInvite);
    }


    @Override
    public void onLoadingShow() {
        showLoading();
    }


    @Override
    public void setMessage(String msg) {
        showToast(msg);
        dismissLoadding();
    }

    @Override
    public void onViewMoreClick(EventInvite eventInvite) {

        //todo eventInvitation Detail
    }

    @Override
    public void onDeleteEventInvite(EventInvite eventInvite) {

        eventInvitationPresenter.delEventInvitation(eventInvite);

    }


}

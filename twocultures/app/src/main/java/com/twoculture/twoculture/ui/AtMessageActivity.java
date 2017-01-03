package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.AtMessageAdapter;
import com.twoculture.twoculture.models.AtNotificationList;
import com.twoculture.twoculture.presenter.IMessageAtPresenter;
import com.twoculture.twoculture.presenter.MessageAtPresenter;
import com.twoculture.twoculture.tools.ViewHelper;
import com.twoculture.twoculture.views.IAtMessageView;

import butterknife.BindView;

public class AtMessageActivity extends BaseActivity implements IAtMessageView {

    private IMessageAtPresenter messageAtPresenter;

    @BindView(R.id.rv_at_message)
    RecyclerView rvAtMessage;

    private AtMessageAdapter atMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();

    }

    @Override
    protected String getTitleName() {
        return this.getString(R.string.message_message_at);
    }

    private void loadData() {
        messageAtPresenter = new MessageAtPresenter(this);
        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAtMessage.setLayoutManager(layoutManager);
        atMessageAdapter = new AtMessageAdapter(this);
        rvAtMessage.setAdapter(atMessageAdapter);
        messageAtPresenter.getAtMessages(1);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_at_message;
    }

    @Override
    public void onLoadSuccess(AtNotificationList notificationList) {
        if (notificationList != null && notificationList.at_notifications.size() > 0) {
            atMessageAdapter.addData(notificationList.at_notifications);
        } else {
            showToast(this.getString(R.string.no_data_hint));
        }
        dismissLoadding();
    }

    @Override
    public void onLoadingShow() {
        if (loadingDialog == null) {
            loadingDialog = ViewHelper.show(this);
        }
    }

    @Override
    public void setMessage(String msg) {
        showToast(msg);
        dismissLoadding();
    }
}

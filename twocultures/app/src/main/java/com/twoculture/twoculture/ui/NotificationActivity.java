package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.NotificationAdapter;
import com.twoculture.twoculture.models.SystemMessage;
import com.twoculture.twoculture.presenter.INotificationPresenter;
import com.twoculture.twoculture.presenter.NotificationPresenter;
import com.twoculture.twoculture.tools.ViewHelper;
import com.twoculture.twoculture.views.INotificationView;

import java.util.List;

import butterknife.BindView;


public class NotificationActivity extends BaseActivity implements INotificationView {

    private INotificationPresenter mNotificationPresenter;

    private NotificationAdapter mNotificationAdapter;
    @BindView(R.id.rv_notification)
    RecyclerView mRvNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        loadData();
    }

    private void loadData() {
        mNotificationPresenter = new NotificationPresenter(this);
        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvNotification.setLayoutManager(layoutManager);
        mNotificationAdapter = new NotificationAdapter();
        mNotificationPresenter.getNotificationList();
        mRvNotification.setAdapter(mNotificationAdapter);
    }

    @Override
    public void onLoadSuccess(List<SystemMessage> systemMessages) {
        if (systemMessages != null && systemMessages.size() > 0) {
            mNotificationAdapter.addDatas(systemMessages);
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

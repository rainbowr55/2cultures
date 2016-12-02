package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.FriendRequestAdapter;
import com.twoculture.twoculture.models.BaseResponse;
import com.twoculture.twoculture.models.FriendRequest;
import com.twoculture.twoculture.presenter.FriendRequestPresenter;
import com.twoculture.twoculture.presenter.IFriendRequestPresenter;
import com.twoculture.twoculture.tools.ViewHelper;
import com.twoculture.twoculture.views.IFriendRequestView;

import java.util.List;

import butterknife.BindView;

public class FriendRequestActivity extends BaseActivity implements IFriendRequestView {

    private IFriendRequestPresenter mFriendRequestPresenter;

    private FriendRequestAdapter friendRequestAdapter;
    @BindView(R.id.rv_friend_request)
    RecyclerView mRvFriendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        loadData();
    }

    private void loadData() {
        mFriendRequestPresenter = new FriendRequestPresenter(this);
        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFriendRequest.setLayoutManager(layoutManager);
        // 设置适配器
        friendRequestAdapter = new FriendRequestAdapter(this::handleFriendRequest);
        mFriendRequestPresenter.getFriendRequestList();
        mRvFriendRequest.setAdapter(friendRequestAdapter);
    }

    public void handleFriendRequest(int messageId) {
        //处理好友请求
        Log.e("test", "messageid=" + messageId);
        mFriendRequestPresenter.processFriendRequest(messageId, FriendRequestPresenter.FRIEND_ACCEPTED);
    }


    @Override
    public void onLoadingShow() {
        if (loadingDialog == null) {
            loadingDialog = ViewHelper.show(this);
        }
    }

    @Override
    public void onLoadSuccess(List<FriendRequest> friendRequest) {
        if (friendRequest != null && friendRequest.size() > 0) {
            friendRequestAdapter.addDatas(friendRequest);
        } else {
            showToast(this.getString(R.string.no_data_hint));
        }
        dismissLoadding();

    }

    @Override
    public void onProcessFriendRequest(int messageId, BaseResponse response) {
        if (response.status == FriendRequestPresenter.FRIEND_ACCEPTED) {
            friendRequestAdapter.deleteItem(messageId);
        }
        showToast(response.msg);
    }

    @Override
    public void setMessage(String msg) {
        showToast(msg);
        dismissLoadding();
    }

    // 点击的回调
    public interface FriendRequestClickCallback {
        void onItemClicked(int messageId);
    }

}

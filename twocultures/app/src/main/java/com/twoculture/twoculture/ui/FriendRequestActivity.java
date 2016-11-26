package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.FriendRequestAdapter;
import com.twoculture.twoculture.presenter.FriendRequestPresenter;
import com.twoculture.twoculture.presenter.IFriendRequestPresenter;
import com.twoculture.twoculture.views.IFriendRequestView;

import butterknife.BindView;

public class FriendRequestActivity extends BaseActivity implements IFriendRequestView {

    private IFriendRequestPresenter mFriendRequestPresenter;

    @BindView(R.id.rv_friend_request) RecyclerView mRvFriendRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
//        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mFriendRequestPresenter = new FriendRequestPresenter(this);
        // 设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFriendRequest.setLayoutManager(layoutManager);

        // 设置适配器
        FriendRequestAdapter adapter = new FriendRequestAdapter(this::handleFriendRequest);
        mFriendRequestPresenter.getFriendRequestList(adapter);
        mRvFriendRequest.setAdapter(adapter);
    }

    private void initView() {


    }

    public void handleFriendRequest(String name){

    }

    @Override
    public void onPregressShow() {

    }

    @Override
    public void onLoadSucuess() {

    }

    @Override
    public void setMessage(String msg) {

    }

    // 点击的回调
    public interface FriendRequestClickCallback {
        void onItemClicked(String name);
    }

}

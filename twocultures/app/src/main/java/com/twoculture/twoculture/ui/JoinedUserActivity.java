package com.twoculture.twoculture.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.JoinedUserAdapter;
import com.twoculture.twoculture.models.EventUsersListResponse;
import com.twoculture.twoculture.presenter.JoinedUserPresenter;
import com.twoculture.twoculture.views.IJoinedUserView;

import java.util.List;

import butterknife.BindView;

public class JoinedUserActivity extends BaseActivity implements IJoinedUserView {

    public static final String EVENT_ID = "eventid";
    @BindView(R.id.rv_users)
    RecyclerView mRecylerViewUsers;

    private JoinedUserAdapter mAdapter;

    private JoinedUserPresenter mJoinedUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJoinedUserPresenter = new JoinedUserPresenter(this);
        mAdapter = new JoinedUserAdapter(this);
        mRecylerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        mRecylerViewUsers.setAdapter(mAdapter);
        int eventId = getIntent().getIntExtra(EVENT_ID,0);
        if(eventId!=0){
            loadUsers(eventId);
        }

    }

    private void loadUsers(int eventId) {
        mJoinedUserPresenter.loadAllUsers(eventId);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_joined_user;
    }


    @Override
    public void refreshList(List<EventUsersListResponse.User> userList) {
        mAdapter.resetData();
        mAdapter.addData(userList);
    }

    @Override
    protected void onDestroy() {
        mJoinedUserPresenter.stopPresenter();
        super.onDestroy();
    }
}

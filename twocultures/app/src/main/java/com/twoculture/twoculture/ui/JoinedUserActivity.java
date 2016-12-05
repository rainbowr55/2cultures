package com.twoculture.twoculture.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;

import butterknife.BindView;

public class JoinedUserActivity extends BaseActivity {

    @BindView(R.id.rv_users)
    RecyclerView mRecylerViewUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_joined_user;
    }


}

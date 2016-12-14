package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.twoculture.twoculture.R;

public class FriendListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        initView();
    }

    private void initView() {
        DiscoverFragment discoverFragment = DiscoverFragment.newInstance(DiscoverFragment.FRIEND_TYPE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_friend_list, discoverFragment);
        fragmentTransaction.commit();
    }
}

package com.twoculture.twoculture.ui;

import android.os.Bundle;

import com.twoculture.twoculture.R;

public class MyProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected String getTitleName() {
        return this.getString(R.string.my_profile_title);
    }

    @Override
    protected String getRightTitle() {
        return this.getString(R.string.my_profile_right_title);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_profile_activiry;
    }
}

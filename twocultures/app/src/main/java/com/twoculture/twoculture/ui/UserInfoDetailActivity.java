package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.twoculture.easemob.Constant;
import com.twoculture.easemob.ui.ChatActivity;
import com.twoculture.twoculture.R;

import butterknife.BindView;

public class UserInfoDetailActivity extends BaseActivity implements View.OnClickListener {

    private String userId;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        userId = getIntent().getStringExtra(Constant.EXTRA_USER_ID);
        tvMessage.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_user_info_detail;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_message:
                if (!TextUtils.isEmpty(userId)) {
                    Intent intent = new Intent(this, ChatActivity.class);
                    // it's single chat
                    intent.putExtra(Constant.EXTRA_USER_ID, userId);
                    startActivity(intent);
                }
                break;
        }

    }
}

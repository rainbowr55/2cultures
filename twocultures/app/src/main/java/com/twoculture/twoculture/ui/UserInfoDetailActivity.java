package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twoculture.base.widget.RoundedImageView;
import com.twoculture.easemob.Constant;
import com.twoculture.easemob.ui.ChatActivity;
import com.twoculture.twoculture.R;

import butterknife.BindView;

public class UserInfoDetailActivity extends BaseActivity implements View.OnClickListener {

    private String userId;

    @BindView(R.id.activity_user_info_detail)
    LinearLayout activityUserInfoDetail;
    @BindView(R.id.iv_user_head)
    RoundedImageView ivUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_gender)
    TextView tvUserGender;
    @BindView(R.id.tv_user_from)
    TextView tvUserFrom;
    @BindView(R.id.tv_user_live_in)
    TextView tvUserLiveIn;
    @BindView(R.id.tv_user_migrate)
    TextView tvUserMigrate;
    @BindView(R.id.tv_user_speak)
    TextView tvUserSpeak;
    @BindView(R.id.tv_user_language)
    TextView tvUserLanguage;
    @BindView(R.id.tv_user_learn_language)
    TextView tvUserLearnLanguage;
    @BindView(R.id.tv_user_relationship_status)
    TextView tvUserRelationshipStatus;
    @BindView(R.id.rl_single_profile)
    RelativeLayout rlSingleProfile;
    @BindView(R.id.rl_topic)
    RelativeLayout rlTopic;
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

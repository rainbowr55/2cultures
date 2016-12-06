package com.twoculture.twoculture.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.tools.RoundedImageView;

import butterknife.BindView;

public class MyProfileActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_add_head_image)
    RoundedImageView ivAddHeadImage;
    @BindView(R.id.rl_profile_name)
    RelativeLayout rlProfileName;
    @BindView(R.id.rl_profile_gender)
    RelativeLayout rlProfileGender;
    @BindView(R.id.rl_profile_from)
    RelativeLayout rlProfileFrom;
    @BindView(R.id.rl_profile_living_in)
    RelativeLayout rlProfileLivingIn;
    @BindView(R.id.rl_profile_migrate_to)
    RelativeLayout rlProfileMigrateTo;
    @BindView(R.id.rl_profile_speak)
    RelativeLayout rlProfileSpeak;
    @BindView(R.id.rl_profile_language)
    RelativeLayout rlProfileLanguage;
    @BindView(R.id.rl_profile_learning_language)
    RelativeLayout rlProfileLearningLanguage;
    @BindView(R.id.rl_profile_relationship)
    RelativeLayout rlProfileRelationship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        tvTopRight.setOnClickListener(this);
        rlProfileName.setOnClickListener(this);
        rlProfileGender.setOnClickListener(this);
        rlProfileFrom.setOnClickListener(this);
        rlProfileLivingIn.setOnClickListener(this);
        rlProfileMigrateTo.setOnClickListener(this);
        rlProfileSpeak.setOnClickListener(this);
        rlProfileLanguage.setOnClickListener(this);
        rlProfileLearningLanguage.setOnClickListener(this);
        rlProfileRelationship.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_top_right://save

                break;
            case R.id.rl_profile_name://name
                break;
            case R.id.rl_profile_gender:
                break;
            case R.id.rl_profile_from:
                break;
            case R.id.rl_profile_living_in:
                break;
            case R.id.rl_profile_migrate_to:
                break;
            case R.id.rl_profile_speak:
                break;
            case R.id.rl_profile_language:
                break;
            case R.id.rl_profile_learning_language:
                break;
            case R.id.rl_profile_relationship:
                break;
            default:
                break;
        }
    }
}

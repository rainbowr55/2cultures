package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twoculture.base.blur.PicassoTransformation;
import com.twoculture.base.widget.LoadingDialog;
import com.twoculture.base.widget.RoundedImageView;
import com.twoculture.base.widget.RoundedTransformationBuilder;
import com.twoculture.base.widget.ToastUtil;
import com.twoculture.easemob.Constant;
import com.twoculture.easemob.ui.ChatActivity;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.SingleProfile;
import com.twoculture.twoculture.models.UserDetail;
import com.twoculture.twoculture.models.UserProfile;
import com.twoculture.twoculture.presenter.UserDetailPresenter;
import com.twoculture.twoculture.views.IUserDetail;

import butterknife.BindView;

public class UserInfoDetailActivity extends BaseActivity implements View.OnClickListener, IUserDetail {


    protected static final int BLUR_RADIUS = 25;
    private String userId;

    @BindView(R.id.iv_user_detail_bg)
    ImageView ivUserDetailBg;
    //    @BindView(R.id.activity_user_info_detail)
//    LinearLayout activityUserInfoDetail;
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

    private UserDetailPresenter userDetailPresenter;
    private SingleProfile singleProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        userDetailPresenter = new UserDetailPresenter(this);
        userDetailPresenter.getUserDetail(userId);
    }

    private void initView() {
        userId = getIntent().getStringExtra(Constant.EXTRA_USER_ID);
        tvMessage.setOnClickListener(this);
        rlSingleProfile.setOnClickListener(this);
        rlTopic.setOnClickListener(this);
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
            case R.id.rl_single_profile:
                Intent intent = new Intent(this, SingleProfileActivity.class);
                if (singleProfile != null) {
                    intent.putExtra(SingleProfileActivity.EXTRA_SINGLE_KEY, singleProfile);
                }
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onLoadSuccess(UserDetail userDetail) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (userDetail == null) {
            return;
        }
        this.singleProfile = userDetail.single_profile;
        if (this.singleProfile == null) {
            this.singleProfile = new SingleProfile();
        }
        this.singleProfile.header_image_url = userDetail.header_image_url;
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.WHITE)
                .borderWidthDp(1)
                .cornerRadiusDp(60)
                .oval(false)
                .build();
        Picasso picasso = Picasso.with(this);
        picasso.load(userDetail.header_image_url)
                .fit()
                .transform(transformation)
                .placeholder(R.drawable.default_gravatar).fit().centerCrop().into(ivUserHead);

        picasso.load(userDetail.header_image_url)
                .fit()
                .noFade()
                .config(Bitmap.Config.ARGB_8888)
                .transform(new PicassoTransformation(this, BLUR_RADIUS))
                .placeholder(R.mipmap.user_detail_bg)
                .into(ivUserDetailBg);
        tvUserRelationshipStatus.setText(userDetail.status);
        tvUserName.setText(userDetail.name);
        initUserProfile(userDetail.user_profile);
    }


    private void initUserProfile(UserProfile userProfile) {

        if (userProfile == null) {
            return;
        }

        if (userProfile.gender == 0) {//female
            tvUserGender.setText(getString(R.string.gender_female));
        } else {
            tvUserGender.setText(getString(R.string.gender_male));
        }
        tvUserFrom.setText(userProfile.from_city_str);
        tvUserLiveIn.setText(userProfile.living_city_str);
        tvUserMigrate.setText(userProfile.migrate_to_city_str);
        tvUserSpeak.setText(userProfile.speak);
        tvUserLanguage.setText(userProfile.second_language);
        tvUserLearnLanguage.setText(userProfile.learning_Language);

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadingShow() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.show(this, "", false, null);
        } else {
            loadingDialog.show();
        }
    }

    @Override
    public void setMessage(String msg) {
        ToastUtil.showMiddleToast(this, msg);
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}

package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.twoculture.base.utils.SecureSharedPreferences;
import com.twoculture.easemob.Constant;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.tools.AppConstants;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    private static final int sleepTime = 2000;

    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    @BindView(R.id.iv_two_culture_logo)
    ImageView ivTwoCultureLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        activitySplash.startAnimation(animation);
    }

    private void initData() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
        checkToken();
    }

    public void checkToken() {
//        SharedPreferences sharedPreferences = TwoCApplication.applicationContext.getSharedPreferences(Constant.TOKEN_FILE_NAME, Context.MODE_PRIVATE);
//        String token = sharedPreferences.getString(Constant.TOKEN_FIELD_NAME, "");
//        boolean isInit = sharedPreferences.getBoolean(Constant.USER_INIT_STATE, false);
        SecureSharedPreferences settings = new SecureSharedPreferences(this);
        boolean isInit = settings.getBoolean(Constant.USER_INIT_STATE, false);
        String token = settings.getString(Constant.TOKEN_FIELD_NAME, "");
        if (!TextUtils.isEmpty(token)) {
            AppConstants.TOKEN = token;
            if (isInit) {
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                this.finish();
            } else {
                Intent intent = new Intent(this, MyProfileActivity.class);
                startActivity(intent);
                this.finish();
            }
        } else {
            Intent loginIntent = new Intent(this, LaunchActivity.class);
            startActivity(loginIntent);
            this.finish();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }
}

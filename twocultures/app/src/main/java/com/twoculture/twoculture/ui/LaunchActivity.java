package com.twoculture.twoculture.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twoculture.easemob.Constant;
import com.twoculture.easemob.TwoCApplication;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.tools.AppConstants;

import butterknife.BindView;

public class LaunchActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_login_email)
    Button mButtonLoginEmail;
    @BindView(R.id.btn_sign_up)
    Button mButtonSignUp;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        initListenter();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) this.findViewById(R.id.btn_login_facebook);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("facebook","onSuccess");
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("facebook","onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("facebook","onError");
            }
        });
        checkToken();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_launch;
    }

    private void initListenter() {
        mButtonLoginEmail.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_email:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_up:
                Intent emailIntent = new Intent(this, SignupActivity.class);
                startActivity(emailIntent);
                break;
            case R.id.btn_test:
                Intent intenttest = new Intent(this, com.twoculture.easemob.ui.SplashActivity.class);
                startActivity(intenttest);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void checkToken(){
        // Restore preferences

        SharedPreferences sharedPreferences = TwoCApplication.applicationContext.getSharedPreferences(Constant.TOKEN_FILE_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Constant.TOKEN_FIELD_NAME,"");
        if(!TextUtils.isEmpty(token)){
            AppConstants.TOKEN = token;
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
}

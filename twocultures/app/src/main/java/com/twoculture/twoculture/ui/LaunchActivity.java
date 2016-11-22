package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.twoculture.twoculture.R;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login_original;
    Button btn_login_email;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_launch);
        initView();
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
    }

    private void initView() {
        btn_login_original = (Button) this.findViewById(R.id.btn_login_original);
        btn_login_email = (Button) this.findViewById(R.id.btn_login_email);
    }

    private void initListenter() {
        btn_login_original.setOnClickListener(this);
        btn_login_email.setOnClickListener(this);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_original:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login_email:
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
}

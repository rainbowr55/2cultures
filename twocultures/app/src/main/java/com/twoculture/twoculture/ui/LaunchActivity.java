package com.twoculture.twoculture.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twoculture.easemob.TwoCApplication;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.presenter.ILoginPresenter;
import com.twoculture.twoculture.presenter.ISignupPresenter;
import com.twoculture.twoculture.presenter.LoginPresenter;
import com.twoculture.twoculture.presenter.SignupPresenter;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.views.ILoginView;
import com.twoculture.twoculture.views.ISignupView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class LaunchActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_login_email)
    Button mButtonLoginEmail;


    @BindView(R.id.btn_sign_up)
    Button mButtonSignUp;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        AppEventsLogger.activateApp(this);
        initListenter();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) this.findViewById(R.id.btn_login_facebook);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserFacebookBasicInfo();
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
                Intent intent = new Intent(this, EmailLoginActivity.class);
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

        SharedPreferences sharedPreferences = GlobalApplication.applicationContext.getSharedPreferences(AppConstants.TOKEN_FILE_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(AppConstants.TOKEN_FIELD_NAME,"");
        if(!TextUtils.isEmpty(token)){
            AppConstants.TOKEN = token;
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

    /**
     *  获取用户的基本信息，比如姓名，年龄，userId，性别等等
     */
    public   void getUserFacebookBasicInfo() {
        // 获取基本文本信息
        Log.i("AlexFB", "准备获取facebook用户基本信息");

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("test","sss");
                JSONObject responseJsonObject = response.getJSONObject();
                FaceBookUserInfo userInfo = new FaceBookUserInfo();
                userInfo.id = getFacebookGraphResponseString(responseJsonObject, "id");
                userInfo.firstName = getFacebookGraphResponseString(responseJsonObject, "first_name");
                userInfo.lastName = getFacebookGraphResponseString(responseJsonObject, "last_name");
                userInfo.userName = getFacebookGraphResponseString(responseJsonObject, "name");
                userInfo.birthday = getFacebookGraphResponseString(responseJsonObject, "birthday");
                userInfo.updateTime = getFacebookGraphResponseString(responseJsonObject, "updated_time");
                userInfo.email = getFacebookGraphResponseString(responseJsonObject, "email");
                userInfo.gender = getFacebookGraphResponseString(responseJsonObject, "gender");

                ISignupPresenter signupPresenter = new SignupPresenter(new ISignupView() {
                    @Override
                    public void onSignupSuccess() {
                        LaunchActivity.this.finish();
                        Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                        LaunchActivity.this.startActivity(intent);

                    }

                    @Override
                    public void onSingupFailed(String message) {
                        Toast.makeText(LaunchActivity.this,message,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLoadingShow() {

                    }

                    @Override
                    public void setMessage(String msg) {

                    }
                });
                ILoginPresenter loginPresenter = new LoginPresenter(new ILoginView() {
                    @Override
                    public void customShowProgress(boolean isShow) {

                    }

                    @Override
                    public void onLoginSuccess() {
                        LaunchActivity.this.finish();
                        Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                        LaunchActivity.this.startActivity(intent);
                    }

                    @Override
                    public void onLoginFailed(String message) {
                        signupPresenter.signup(userInfo.email,userInfo.email,"en");
                    }

                    @Override
                    public void onLoadingShow() {

                    }

                    @Override
                    public void setMessage(String msg) {

                    }
                });
                loginPresenter.loginToServer(userInfo.email,userInfo.email,"en", "default-token");

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,first_name,last_name,gender,locale,timezone,updated_time,verified");
        request.setParameters(parameters);
        request.executeAsync();
    }


    public static class FaceBookUserInfo {
        public String id;
        public String firstName;
        public String lastName;
        public String userName;
        public String birthday;
        public String location;
        public String updateTime;
        public String email;
        public String gender;
        public String avatar;//头像url
    }

    private static String getFacebookGraphResponseString(JSONObject graphResponse, String flag) {
        String value = "";
        try {
            value = graphResponse.getString(flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }
}

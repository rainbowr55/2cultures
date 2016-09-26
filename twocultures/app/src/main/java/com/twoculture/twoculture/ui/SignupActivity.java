package com.twoculture.twoculture.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.SignupResult;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.StringUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignupActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private Spinner sp_locale;
    private Button  btn_signup;
    private LinearLayout ll_home;
    private Subscription mSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        initView();
    }

    public void initView(){
        ll_home = (LinearLayout) this.findViewById(R.id.ll_home);
        et_email = (EditText) this.findViewById(R.id.et_email);
        et_password = (EditText) this.findViewById(R.id.et_password);
        sp_locale = (Spinner) this.findViewById(R.id.sp_locale);
        btn_signup = (Button) this.findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup(){
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
       if( !StringUtils.isEmailValid(email)){
           Toast.makeText(SignupActivity.this,"email address is not valid.",Toast.LENGTH_SHORT).show();
           return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SignupActivity.this,"password conn't be null!",Toast.LENGTH_SHORT).show();
            return;
        }
        String locale =  sp_locale.getSelectedItem().toString();
        mSubscription = RxClient.getInstance().signup(email,password,locale)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<SignupResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SignupResult signupResult) {
                Log.d(SignupActivity.this.getClass().getName(),signupResult.msg);
                Toast.makeText(SignupActivity.this,signupResult.msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

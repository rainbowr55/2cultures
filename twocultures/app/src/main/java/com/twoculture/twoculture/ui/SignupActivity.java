package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.presenter.ISignupPresenter;
import com.twoculture.twoculture.presenter.SignupPresenter;
import com.twoculture.twoculture.views.ISignupView;

import rx.Subscription;

public class SignupActivity extends AppCompatActivity implements ISignupView {

    private EditText et_email;
    private EditText et_password;
    private Spinner sp_locale;
    private Button btn_signup;
    private LinearLayout ll_home;
    private Subscription mSubscription;

    private ISignupPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        mPresenter = new SignupPresenter(this);
        initView();
    }

    public void initView() {
        ll_home = (LinearLayout) this.findViewById(R.id.ll_home);
        et_email = (EditText) this.findViewById(R.id.et_email);
        et_password = (EditText) this.findViewById(R.id.et_password);
        sp_locale = (Spinner) this.findViewById(R.id.sp_locale);
        btn_signup = (Button) this.findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String locale = sp_locale.getSelectedItem().toString();
                mPresenter.signup(email, password, locale);
            }
        });
    }

    private void signup() {

    }

    @Override
    public void setMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignupSuccess() {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        SignupActivity.this.finish();
    }
}

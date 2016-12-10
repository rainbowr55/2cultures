package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;
import rx.Subscription;

public class SignupActivity extends BaseActivity implements ISignupView {

    @BindView(R.id.et_email)
    EditText mEditEmail;

    @BindView(R.id.et_password)
    EditText mEditPassword;

    @BindView(R.id.sp_locale)
    Spinner mSpinnerLocale;

    @BindView(R.id.btn_signup)
    Button mButtonSignup;

    @BindView(R.id.ll_home)
    LinearLayout mLinearLayoutHome;

    private Subscription mSubscription;

    private ISignupPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SignupPresenter(this);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_singup;
    }

    public void initView() {
        mLinearLayoutHome = (LinearLayout) this.findViewById(R.id.ll_home);
        mEditEmail = (EditText) this.findViewById(R.id.et_email);
        mEditPassword = (EditText) this.findViewById(R.id.et_password);
        mSpinnerLocale = (Spinner) this.findViewById(R.id.sp_locale);
        mButtonSignup = (Button) this.findViewById(R.id.btn_signup);
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString().trim();
                String password = mEditPassword.getText().toString().trim();
                String locale = mSpinnerLocale.getSelectedItem().toString();
                mPresenter.signup(email, password, locale);
            }
        });
    }

    private void signup() {

    }

    @Override
    public void onLoadingShow() {

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

    @Override
    public void onSingupFailed(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

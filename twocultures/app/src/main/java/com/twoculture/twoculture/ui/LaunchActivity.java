package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.twoculture.twoculture.R;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login_original;
    Button btn_login_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
        initListenter();
    }

    private void initView() {
        btn_login_original = (Button) this.findViewById(R.id.btn_login_original);
        btn_login_email = (Button) this.findViewById(R.id.btn_login_email);
    }

    private void initListenter() {
        btn_login_original.setOnClickListener(this);
        btn_login_email.setOnClickListener(this);
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
        }
    }
}

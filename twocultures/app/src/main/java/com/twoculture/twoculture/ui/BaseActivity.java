package com.twoculture.twoculture.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twoculture.base.widget.ToastUtil;
import com.twoculture.twoculture.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Dialog loadingDialog;

    RelativeLayout rlTopTitle;
    TextView tvTopMiddle;
    TextView tvTopRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        rlTopTitle = (RelativeLayout) findViewById(R.id.rl_top_title);
        tvTopMiddle = (TextView) findViewById(R.id.tv_top_middle);
        tvTopRight = (TextView) findViewById(R.id.tv_top_right);
        if (tvTopMiddle != null) {
            tvTopMiddle.setText(getTitleName());
        }
        if (tvTopRight != null) {
            tvTopRight.setText(getRightTitle());
        }
        if (rlTopTitle != null) {
            rlTopTitle.setVisibility(View.VISIBLE);
        }
    }

    protected String getTitleName() {
        return "";
    }

    protected String getRightTitle() {
        return "";
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected void dismissLoadding() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    protected void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.showMiddleToast(this, msg);
        }
    }
}

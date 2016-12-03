package com.twoculture.twoculture.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.twoculture.base.widget.ToastUtil;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

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

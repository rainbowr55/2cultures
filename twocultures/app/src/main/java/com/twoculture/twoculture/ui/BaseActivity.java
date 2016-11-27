package com.twoculture.twoculture.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.twoculture.base.widget.ToastUtil;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    protected Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

    }

    protected void dismissLoadding(){
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    protected void showToast(String msg){
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.showMiddleToast(this, msg);
        }
    }
}

package com.twoculture.base.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager.LayoutParams;

import com.twoculture.base.R;

/**
 * Created by rainbow on 16/11/25.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_twoculture_loading);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, R.style.Theme_twoculture_loading);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutParams lp = getWindow().getAttributes();
        lp.windowAnimations = R.style.Anim_Loading_Dialog;
        lp.flags = LayoutParams.FLAG_DIM_BEHIND;
        lp.dimAmount = 0.0f;
        setContentView(R.layout.dialog_loadding);
    }

    public static LoadingDialog show(Context context, String message, boolean cancelable,
                                     OnCancelListener cancelListener) {
        if (context == null) {
            return null;
        }
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return null;
        }
        LoadingDialog loadingDialog = new LoadingDialog(context);
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setOnCancelListener(cancelListener);
        loadingDialog.show();
        return loadingDialog;
    }

    // 解决4.0以上系统加载时点击屏幕会取消情况
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}

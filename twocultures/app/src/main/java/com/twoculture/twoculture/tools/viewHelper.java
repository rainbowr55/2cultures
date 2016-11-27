package com.twoculture.twoculture.tools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.twoculture.base.widget.LoadingDialog;

/**
 * Created by rainbow on 16/11/25.
 */

public class ViewHelper {



    public static LoadingDialog show(Context context) {
        return show(context,"",false,null);
    }

    public static LoadingDialog show(Context context, String message, boolean cancelable,
                                     DialogInterface.OnCancelListener cancelListener) {
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
}

package com.twoculture.twoculture.tools;

import android.app.Activity;
import android.content.DialogInterface;

import com.twoculture.base.widget.LoadingDialog;
import com.twoculture.twoculture.views.IShowMessage;

/**
 * Created by rainbow on 16/11/25.
 */

public class ViewHelper {



    public static LoadingDialog show(IShowMessage iMessage) {
        return show(iMessage,"",false,null);
    }

    public static LoadingDialog show(IShowMessage iMessage, String message, boolean cancelable,
                                     DialogInterface.OnCancelListener cancelListener) {
        if (iMessage == null) {
            return null;
        }
        if ((iMessage instanceof Activity) && ((Activity) iMessage).isFinishing()) {
            return null;
        }
        Activity activity = (Activity) iMessage;
        LoadingDialog loadingDialog = new LoadingDialog(activity);
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setOnCancelListener(cancelListener);
        loadingDialog.show();
        return loadingDialog;
    }
}

package com.twoculture.base.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.twoculture.base.R;

/**
 * Created by rainbow on 16/11/27.
 */

public class ToastUtil {

    /**
     * 显示提示
     *
     * @param context
     * @param msg     提示内容
     */
    public static void showToast(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        showMiddleToast(context, "", msg);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    /**
     * 显示居中的提交
     *
     * @param context
     * @param title   提示标题
     * @param msg     提示内容
     */
    public static void showMiddleToast(Context context, String title, String msg) {
        if (msg == null || msg.equals("") || msg.length() == 0)
            return;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.common_toast_title);
        TextView tvMsg = (TextView) view.findViewById(R.id.common_toast_msg);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        tvMsg.setText(msg);
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM, 0, 120);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Toast
     *
     * @param context
     * @param msg
     */
    public static void showMiddleToast(Context context, String msg) {
        showMiddleToast(context, "", msg);
    }
}

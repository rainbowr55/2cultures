package com.twoculture.twoculture.tools;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by liangcaihong on 2016/12/6.
 */

public class CLog {

    public static void log(String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            System.out.println(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (AppConstants.DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void wtf(Exception e) {
        if (AppConstants.DEBUG && e != null) {
            Log.e("Gerror", "error message=" + e.getMessage());
        }
    }
}

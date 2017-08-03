package com.base.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/4/7.
 */

public class LoggerUtil {
    private static final String TAG = "LoggerUtil";
    private static boolean isDebug = true;

    public static void setDebug(boolean isDebugShow) {
        isDebug = isDebugShow;
    }

    public static void d(String message) {
        if (!isDebug || message == null) {
            return;
        }
        Log.d(TAG, message);
    }

    public static void i(String message) {
        if (!isDebug || message == null) {
            return;
        }
        Log.i(TAG, message);
    }

    public static void e(String message) {
        if (!isDebug || message == null) {
            return;
        }
        Log.e(TAG, message);
    }

    public static void v(String message) {
        if (!isDebug || message == null) {
            return;
        }
        Log.v(TAG, message);
    }


}

package com.lhy.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static SharedPreferences msp = null;

    private static SharedPreferences getSharePreference(Context context) {
        if (msp == null) {
            msp = context.getSharedPreferences("wuyoujie", Context.MODE_PRIVATE);
        }
        return msp;
    }

    /**
     * 获取用户ID
     *
     * @param context
     * @return
     */
    public static String getUid(Context context) {
        return "2";
//        return getString(context, "uid", null);
    }

    /**
     * 保存软件参数
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("wuyoujie", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 得到软件保存的参数
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("wuyoujie", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void putString(Context context, String key, String value) {
        getSharePreference(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        return getSharePreference(context).getString(key, defValue);
    }

    public static void clearData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("wuyoujie", Context.MODE_PRIVATE);
        sp.edit().remove("uid").commit();
    }

    /**
     * int保存
     *
     * @param context
     * @param key
     * @return
     */
    public static void putInt(Context context, String key, int values) {
        SharedPreferences sp = context.getSharedPreferences("wuyoujie", context.MODE_PRIVATE);
        sp.edit().putInt(key, values).commit();
    }

    /**
     * int读取
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("wuyoujie", Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }
}
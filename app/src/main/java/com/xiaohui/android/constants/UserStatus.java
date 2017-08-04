package com.xiaohui.android.constants;

import android.content.Context;

import com.lhy.baselib.utils.SpUtils;

/**
 * Created by zwy on 2017/5/10.
 * package_name is com.xiaohui.android.constants
 * 描述:用户状态判断
 */

public class UserStatus {
    /**
     * 是否认证
     *
     * @return
     */
    public static boolean isAuth(Context context) {
        int id_status = SpUtils.getInt(context, "id_status");
        int img_status = SpUtils.getInt(context, "img_status");
        if (id_status == 0 || img_status == 0) {
            return false;
        } else {
            return true;
        }
    }
}

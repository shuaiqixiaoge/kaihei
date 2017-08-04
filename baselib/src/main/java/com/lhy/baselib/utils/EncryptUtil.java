package com.lhy.baselib.utils;

/**
 * Created by yeqiu on 2016/12/21.
 */

public class EncryptUtil {

    /**
     * 手机号密文展示
     *
     * @param mobile
     * @return
     */
    public static String encryptPhone(String mobile) {
        if (mobile.length() == 11) {
            return mobile.substring(0, 3) + "****" + mobile.substring(8, 11);
        }
        return mobile;
    }
}

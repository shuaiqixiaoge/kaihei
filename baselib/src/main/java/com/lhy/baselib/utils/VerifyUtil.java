package com.lhy.baselib.utils;

/**
 * Created by yeqiu on 2016/12/13.
 */

public class VerifyUtil {
    /**
     * 手机号格式验证
     *
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (mobile == null || mobile.equals("")) {
            return false;
        }
        String telRegex1 = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex2 = "[1][4][7]\\d{10}";
        return mobile.matches(telRegex1) || mobile.matches(telRegex2);
    }

    /**
     * 邮箱格式验证
     *
     * @return
     */
    public static boolean isEmail(String email) {
        return true;
    }
}

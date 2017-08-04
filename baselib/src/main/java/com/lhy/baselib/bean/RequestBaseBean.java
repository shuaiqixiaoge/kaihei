package com.lhy.baselib.bean;


import android.util.Log;

import com.lhy.baselib.utils.LoggerUtil;
import com.lhy.baselib.utils.SPCommonUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 * 需要加密的接口全部需要继承此类
 */

public abstract class RequestBaseBean implements Serializable {
    public static final String sign_key = "ycld";

    private String time;
    private String sign;

    public RequestBaseBean() {

    }

    public void setSignValue() {
        this.time = SPCommonUtils.getTime();
        this.sign = SPCommonUtils.signParameter(getMap(), sign_key);
    }

    protected abstract Map getMap();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
package com.base.net.service;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net.service
 * 描述:SJ
 */

public interface ApiCallBack<P> {
    void onStart();

    void onSuccess(P data);

    void onFailed(int errorCode, String msg);
}

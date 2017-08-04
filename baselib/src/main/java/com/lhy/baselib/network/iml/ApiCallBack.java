package com.lhy.baselib.network.iml;

public interface ApiCallBack<P> {
    void onSuccess(P data);

    void onFailure(int code, String msg);

    void onStartRequest();
}
package com.lhy.baselib.network;


/**
 * Created by zwy on 2017/3/31.
 */

public class HttpBaseBean<P> {
    private int status;
    private String msg;
    private P data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public P getData() {
        return data;
    }

    public void setData(P data) {
        this.data = data;
    }
}

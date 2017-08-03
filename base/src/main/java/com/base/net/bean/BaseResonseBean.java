package com.base.net.bean;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net.bean
 * 描述:SJ
 */

public class BaseResonseBean<T> {
    private String msg;
    private int status;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.lhy.baselib.exception;

/**
 * Created by zwy on 2017/5/3.
 * package_name is com.lhy.baselib.exception
 * 描述:XH2
 */

public class HttpApiException extends Exception {
    /*错误码*/
    private int code;
    /*显示的信息*/
    private String displayMessage;

    public HttpApiException(Throwable e) {
        super(e);
    }

    public HttpApiException(Throwable cause, @CodeException.CodeEp int code, String showMsg) {
        super(showMsg, cause);
        setCode(code);
        setDisplayMessage(showMsg);
    }

    @CodeException.CodeEp
    public int getCode() {
        return code;
    }

    public void setCode(@CodeException.CodeEp int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}

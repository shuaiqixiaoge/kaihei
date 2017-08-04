package com.lhy.baselib.event;

import com.lhy.baselib.network.HttpConstants;

/**
 * Created by Administrator on 2017/4/7.
 */

public class MessageEvent<T> {
    public int messageCode;
    public String message;

    public HttpConstants.EventCode getCode() {
        return code;
    }

    public void setCode(HttpConstants.EventCode code) {
        this.code = code;
    }

    public HttpConstants.EventCode code;
    public T messageData;

    public MessageEvent(HttpConstants.EventCode eventCode, int messageCode, String message, T messageData) {
        this.code = eventCode;
        this.messageData = messageData;
        this.messageCode = messageCode;
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getMessageData() {
        return messageData;
    }

    public void setMessageData(T messageData) {
        this.messageData = messageData;
    }
}

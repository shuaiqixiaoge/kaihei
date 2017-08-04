package com.lhy.baselib.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2017/4/26.
 * package_name is com.xiaohui.android.bean.request
 * 描述:XH2
 */

public class RUserIdBean extends RequestBaseBean {
    private String uid;

    public RUserIdBean(String uid) {
        this.uid = uid;
        super.setSignValue();
    }

    @Override
    protected Map getMap() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("uid", getUid());
        return paramsMap;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

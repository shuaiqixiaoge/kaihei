package com.lhy.baselib.bean.requestparams;

import com.lhy.baselib.bean.RequestBaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2017/4/26.
 * package_name is com.xiaohui.android.bean.request
 * 描述:XH2
 */

public class RZXBean extends RequestBaseBean {
    private String uid;
    private int page = 1;

    public RZXBean(String uid, int page) {
        this.uid = uid;
        this.page = page;
        super.setSignValue();
    }

    @Override
    protected Map getMap() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("uid", getUid());
        paramsMap.put("page", String.valueOf(getPage()));
        return paramsMap;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

package com.lhy.baselib.bean.requestparams;

import com.lhy.baselib.bean.RequestBaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwy on 2017/5/5.
 * package_name is com.lhy.baselib.bean.requestparams
 * 描述:校股主页请求数据
 */

public class RXGItemBean extends RequestBaseBean {
    public RXGItemBean(String uid, int page, String user_type, String new_price_sort, String fluctua_range_sort) {
        this.uid = uid;
        this.page = page;
        this.user_type = user_type;
        this.new_price_sort = new_price_sort;
        this.fluctua_range_sort = fluctua_range_sort;
        super.setSignValue();
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

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getNew_price_sort() {
        return new_price_sort;
    }

    public void setNew_price_sort(String new_price_sort) {
        this.new_price_sort = new_price_sort;
    }

    public String getFluctua_range_sort() {
        return fluctua_range_sort;
    }

    public void setFluctua_range_sort(String fluctua_range_sort) {
        this.fluctua_range_sort = fluctua_range_sort;
    }

    private String uid;
    private int page = 1;
    private String user_type;
    private String new_price_sort;
    private String fluctua_range_sort;


    @Override
    protected Map getMap() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("uid", getUid());
        paramsMap.put("page", String.valueOf(getPage()));
        paramsMap.put("user_type", getUser_type());
        paramsMap.put("new_price_sort", getNew_price_sort());
        paramsMap.put("fluctua_range_sort", getFluctua_range_sort());
        return paramsMap;
    }
}

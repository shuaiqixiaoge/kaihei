package com.lhy.baselib.bean.requestparams;

import android.os.Parcel;

import com.lhy.baselib.bean.RequestBaseBean;
import com.lhy.baselib.utils.MD5Transfer;
import com.lhy.baselib.utils.SPCommonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/4/11.
 *
 * @author zwy
 * @date 2017.4.11
 * @describe 登录参数Bean
 */

public class RLoginBean extends RequestBaseBean {

    public RLoginBean(String user_name, String pwd, String device_type) {
        super();
        this.user_name = user_name;
        this.pwd = MD5Transfer.MD5(pwd);
        this.device_type = device_type;
    }

    @Override
    protected Map getMap() {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("user_name", getUser_name());
        paramsMap.put("pwd", getPwd());
        paramsMap.put("device_type", "android");
        return paramsMap;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    private String user_name;
    private String pwd;
    private String device_type;
}

package com.xiaohui.android.bean;

/**
 * Created by zwy on 2017/5/23.
 * package_name is com.xiaohui.android.bean
 * 描述:联系人
 */

public class ContactsBean {

    private String name;
    private String phone ;

    public ContactsBean(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

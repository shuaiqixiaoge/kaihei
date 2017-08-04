package com.xiaohui.android.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.lhy.baselib.utils.LoggerUtil;
import com.xiaohui.android.bean.ContactsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zwy on 2017/5/23.
 * package_name is com.xiaohui.android.util
 * 描述:读取联系人
 */

public class ReadContacts {
    private static List<ContactsBean> list = new ArrayList<>();

    public static List<ContactsBean> readContacts(Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                ContactsBean contactsBean = new ContactsBean(name, phone);
                list.add(contactsBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}

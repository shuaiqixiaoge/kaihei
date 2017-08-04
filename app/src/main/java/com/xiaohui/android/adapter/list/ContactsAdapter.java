package com.xiaohui.android.adapter.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lhy.baselib.utils.LoggerUtil;
import com.xiaohui.android.R;
import com.xiaohui.android.bean.ContactsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwy on 2017/5/23.
 * package_name is com.xiaohui.android.adapter.list
 * 描述:XH2
 */

public class ContactsAdapter extends BaseAdapter {
    private List<ContactsBean> list;

    public ContactsAdapter(List<ContactsBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_address_friend, parent, false);
            holder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            holder.item_phone = (TextView) convertView.findViewById(R.id.item_phone);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        LoggerUtil.e(list.get(position).getName() + "========" + list.get(position).getPhone());
        holder.item_name.setText(list.get(position).getName() + "");
        holder.item_phone.setText(list.get(position).getPhone() + "");
        return convertView;
    }

    class Holder {
        TextView item_name;
        TextView item_phone;
    }
}

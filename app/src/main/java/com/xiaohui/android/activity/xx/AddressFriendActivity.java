package com.xiaohui.android.activity.xx;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import com.gyf.barlibrary.BarManager;
import com.lhy.baselib.utils.LoggerUtil;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.adapter.list.ContactsAdapter;
import com.xiaohui.android.bean.ContactsBean;
import com.xiaohui.android.util.ReadContacts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zwy
 * @describe 通讯录好友
 * @date 2017.5.12
 */
public class AddressFriendActivity extends BaseActivity {
    @BindView(R.id.listView)
    ListView listView;

    private List<ContactsBean> list;
    private ContactsAdapter adapter;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
    }

    @OnClick(R.id.onClick_BACK)
    public void onClick_BACK() {
        finish();
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_address_friend;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        list = ReadContacts.readContacts(this);
        adapter = new ContactsAdapter(list);
        listView.setAdapter(adapter);
    }
}

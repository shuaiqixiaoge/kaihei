package com.xiaohui.android.activity.xx;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.gyf.barlibrary.BarManager;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;

import butterknife.OnClick;

/**
 * Created by zwy on 2017/5/11.
 * package_name is com.xiaohui.android.activity.xx
 * 描述:搜索结果展示
 */

public class SearchResultActivity extends BaseActivity {
    @Override
    public int resLayoutID() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
    }

    @OnClick(R.id.onClick_BACK)
    public void onClick_BACK() {
        finish();
    }

}

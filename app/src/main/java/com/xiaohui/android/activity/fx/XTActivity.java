package com.xiaohui.android.activity.fx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zwy
 * @describe 校淘
 * @date 2017.5.10
 */
public class XTActivity extends BaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    /**
     * 返回
     */
    @OnClick(R.id.onClick_BACK)
    public void onClick_BACK() {
        finish();
    }

    /**
     * 消息
     */
    @OnClick(R.id.onClick_MESSAGE)
    public void onClick_MESSAGE() {
        Intent intent = new Intent();
        startActivity(intent);
    }

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        
    }


    @Override
    public int resLayoutID() {
        return R.layout.activity_xt;
    }

    @Override
    public void initData() {

    }
}

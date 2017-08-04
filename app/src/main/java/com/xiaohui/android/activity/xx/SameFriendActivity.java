package com.xiaohui.android.activity.xx;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.barlibrary.BarManager;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;

import butterknife.OnClick;

/**
 * @author zwy
 * @describe 同校好友
 * @date 2017.5.12
 */
public class SameFriendActivity extends BaseActivity {


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
        return R.layout.activity_same_friend;
    }

    @Override
    public void initData() {

    }
}

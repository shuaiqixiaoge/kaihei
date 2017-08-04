package com.xiaohui.android.activity.xx;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.BarManager;
import com.gyf.barlibrary.BarType;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.util.PermissionUtil;

import butterknife.BindView;

/**
 * @author zwy
 * @describe 添加好友
 * @date 2017.5.11
 */
public class AddFriendActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.onClick_BACK)
    ImageView onClickBACK;
    @BindView(R.id.onClick_SEARCH)
    LinearLayout onClickSEARCH;
    @BindView(R.id.onClick_ADDRESS)
    LinearLayout onClickADDRESS;
    @BindView(R.id.onClick_KNOW)
    LinearLayout onClickKNOW;
    @BindView(R.id.onClick_SAME)
    LinearLayout onClickSAME;
    @BindView(R.id.onClick_SHARE)
    LinearLayout onClickSHARE;

    private PermissionUtil permissionUtil;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
        onClickBACK.setOnClickListener(this);
        onClickSEARCH.setOnClickListener(this);
        onClickADDRESS.setOnClickListener(this);
        onClickKNOW.setOnClickListener(this);
        onClickSAME.setOnClickListener(this);
        onClickSHARE.setOnClickListener(this);
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_add_friend;
    }

    @Override
    public void initData() {
        permissionUtil = new PermissionUtil(AddFriendActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onClick_BACK:
                finish();
                break;
            case R.id.onClick_SEARCH:
                startActivity(FriendSearchActivity.class);
                break;
            case R.id.onClick_ADDRESS:
                if (permissionUtil.requestPermissions(PermissionUtil.READ_CONTACTS, new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS})) {
                    startActivity(AddressFriendActivity.class);
                }
                break;
            case R.id.onClick_KNOW:
                startActivity(KnowFriendActivity.class);
                break;
            case R.id.onClick_SAME:
                startActivity(SameFriendActivity.class);
                break;
            case R.id.onClick_SHARE:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

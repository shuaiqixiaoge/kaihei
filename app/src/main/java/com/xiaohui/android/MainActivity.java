package com.xiaohui.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaohui.android.fragment.FXFragment;
import com.xiaohui.android.fragment.WDFragment;
import com.xiaohui.android.fragment.XGFragment;
import com.xiaohui.android.fragment.XXFragment;
import com.xiaohui.android.fragment.ZXFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.main_zx)
    LinearLayout mainZx;
    @BindView(R.id.main_xg)
    LinearLayout mainXg;
    @BindView(R.id.main_fx)
    LinearLayout mainFx;
    @BindView(R.id.main_xx)
    LinearLayout mainXx;
    @BindView(R.id.main_wd)
    LinearLayout mainWd;

    private List<LinearLayout> llList;

    private ZXFragment zxFragment;
    private XGFragment xgFragment;
    private FXFragment fxFragment;
    private XXFragment xxFragment;
    private WDFragment wdFragment;

    private List<Fragment> fragmentList;
    private int position = 0;//最后位置

    private long indexTime = 0;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        llList = new ArrayList<>();
        mainZx.setOnClickListener(this);
        mainXg.setOnClickListener(this);
        mainFx.setOnClickListener(this);
        mainXx.setOnClickListener(this);
        mainWd.setOnClickListener(this);
        llList.add(mainZx);
        llList.add(mainXg);
        llList.add(mainFx);
        llList.add(mainXx);
        llList.add(mainWd);


        initSaveInstanceState(savedInstanceState);

    }

    /**
     * 初始化保存的装态
     *
     * @param savedInstanceState
     */
    private void initSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
            changeStatus(position);
            changeFragment(fragmentList.get(position));
        }
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        initFragmentList();
        changeStatus(position);
        changeFragment(fragmentList.get(position));
    }


    /**
     * 加载Fragment
     */
    private void initFragmentList() {
        fragmentList = new ArrayList<>();
        zxFragment = ZXFragment.newInstance();
        xgFragment = XGFragment.newInstance();
        fxFragment = FXFragment.newInstance();
        xxFragment = XXFragment.newInstance();
        wdFragment = WDFragment.newInstance();
        fragmentList.add(zxFragment);
        fragmentList.add(xgFragment);
        fragmentList.add(fxFragment);
        fragmentList.add(xxFragment);
        fragmentList.add(wdFragment);
    }


    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_zx:
                position = 0;
                changeStatus(position);
                changeFragment(fragmentList.get(position));
                break;
            case R.id.main_xg:
                position = 1;
                changeStatus(position);
                changeFragment(fragmentList.get(position));
                break;
            case R.id.main_fx:
                position = 2;
                changeStatus(position);
                changeFragment(fragmentList.get(position));
                break;
            case R.id.main_xx:
                position = 3;
                changeStatus(position);
                changeFragment(fragmentList.get(position));
                break;
            case R.id.main_wd:
                position = 4;
                changeStatus(position);
                changeFragment(fragmentList.get(position));
                break;
        }
    }

    /**
     * 修改点击状态
     */
    public void changeStatus(int position) {
        for (int i = 0; i < llList.size(); i++) {
            if (i == position) {
                llList.get(i).setSelected(true);
            } else {
                llList.get(i).setSelected(false);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    /**
     * 点击两次返回退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long now_time = System.currentTimeMillis();
            if ((now_time - indexTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                indexTime = now_time;
                return false;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.xiaohui.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.BarManager;
import com.lhy.baselib.activity.BaseInterface;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (resLayoutID() != 0) {
            setContentView(resLayoutID());
            BarManager.setBarColor(this);
            unbinder = ButterKnife.bind(this);
            onActCreate(savedInstanceState);
        }
    }

    @Override
    public abstract void onActCreate(Bundle savedInstanceState);

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
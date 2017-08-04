package com.lhy.baselib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by zwy on 2017/3/31.
 */

public abstract class BaseLibActivity extends AppCompatActivity implements BaseInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (resLayoutID() != 0) {
            setContentView(resLayoutID());
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

}

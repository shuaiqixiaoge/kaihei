package com.lhy.baselib.activity;

import android.os.Bundle;

/**
 * Created by zwy on 2017/3/31.
 */

public interface BaseInterface {
    int resLayoutID();

    void onActCreate(Bundle savedInstanceState);

    void initData();
}

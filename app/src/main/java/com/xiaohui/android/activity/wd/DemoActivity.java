package com.xiaohui.android.activity.wd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.util.PopWindowUtil;
import com.xiaohui.android.view.AnimTestView;

import butterknife.BindView;

public class DemoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.customText)
    AnimTestView customText;

    private PopWindowUtil popWindowUtil;
    private View convertView;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        popWindowUtil = new PopWindowUtil(DemoActivity.this);
        convertView = LayoutInflater.from(DemoActivity.this).inflate(R.layout.pop_take_pic, null);
        btn.setOnClickListener(this);
        tv.setOnClickListener(this);
        et.setOnClickListener(this);
        customText.setOnClickListener(this);
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_demo;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                popWindowUtil.showPopNoParent(convertView, btn);
                break;
            case R.id.tv:
                popWindowUtil.showBottom(convertView, v);
                break;
            case R.id.et:
                popWindowUtil.showPopParent(convertView, et);
                break;
            case R.id.customText:
                popWindowUtil.showPopNoParent(convertView, customText);
                break;
        }
    }
}

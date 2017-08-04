package com.xiaohui.android.activity.wd;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.gyf.barlibrary.BarManager;
import com.lhy.baselib.meta.CircleTransform;
import com.lhy.baselib.utils.LoggerUtil;
import com.lhy.baselib.utils.TakePicUtil;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.activity.AuthActivity;
import com.xiaohui.android.util.ImageLoader;
import com.xiaohui.android.util.PermissionUtil;
import com.xiaohui.android.util.PopWindowUtil;

import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.onClick_Head)
    RelativeLayout onClickHead;
    @BindView(R.id.onClick_Nick)
    RelativeLayout onClickNick;
    @BindView(R.id.onClick_School)
    RelativeLayout onClickSchool;
    @BindView(R.id.onClick_Birthday)
    RelativeLayout onClickBirthday;
    @BindView(R.id.onClick_Sex)
    RelativeLayout onClickSex;
    @BindView(R.id.onClick_Identify)
    RelativeLayout onClickIdentify;

    @BindView(R.id.info_head)
    ImageView infoHead;
    @BindView(R.id.info_nick)
    TextView infoNick;
    @BindView(R.id.info_school)
    TextView infoSchool;
    @BindView(R.id.info_birthday)
    TextView infoBirthday;
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.info_identify)
    TextView infoIdentify;

    private PermissionUtil permissionUtil;

    private PopWindowUtil popWindowUtil;
    private TakePicUtil takePicUtil;
    private View pop_take_view;
    private LinearLayout pop_pic, pop_take, pop_cancel;

    private ImageView testImg;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
        onClickHead.setOnClickListener(this);
        onClickNick.setOnClickListener(this);
        onClickSchool.setOnClickListener(this);
        onClickBirthday.setOnClickListener(this);
        onClickSex.setOnClickListener(this);
        onClickIdentify.setOnClickListener(this);

        testImg = (ImageView) findViewById(R.id.testImg);

        initPermission();
        initPopWindow();
    }

    /**
     * 初始化弹出框
     */
    private void initPopWindow() {
        takePicUtil = new TakePicUtil(MyInfoActivity.this, TakePicUtil.CropType.Normal);
        popWindowUtil = new PopWindowUtil(MyInfoActivity.this);
        pop_take_view = LayoutInflater.from(MyInfoActivity.this).inflate(R.layout.pop_take_pic, null);
        pop_pic = (LinearLayout) pop_take_view.findViewById(R.id.pop_pic);
        pop_take = (LinearLayout) pop_take_view.findViewById(R.id.pop_take);
        pop_cancel = (LinearLayout) pop_take_view.findViewById(R.id.pop_cancel);

        pop_pic.setOnClickListener(OnClick_Pop);
        pop_take.setOnClickListener(OnClick_Pop);
        pop_cancel.setOnClickListener(OnClick_Pop);
    }

    /**
     * 照相点击
     */
    private View.OnClickListener OnClick_Pop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pop_pic:
                    if (permissionUtil.requestPermissions(PermissionUtil.CAMERA, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                        takePicUtil.chooseSelectPic();
                    }
                    break;
                case R.id.pop_take:
                    if (permissionUtil.requestPermissions(PermissionUtil.CAMERA, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})) {
                        takePicUtil.chooseTakePhoto();
                    }
                    break;
                case R.id.pop_cancel:
                    popWindowUtil.dissmissPop();
                    break;
            }
        }
    };

    /**
     * 初始化权限
     */
    private void initPermission() {
        permissionUtil = new PermissionUtil(MyInfoActivity.this);
    }


    @OnClick(R.id.onClick_BACK)
    public void onClick_BACK() {
        finish();
    }


    @Override
    public int resLayoutID() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initData() {
        ImageLoader.getInstance(MyInfoActivity.this).loadCircleHead(R.drawable.splash_bg, infoHead);
        infoBirthday.setText("2016-5-14");
        infoNick.setText("夜秋的殇");
        infoSex.setText("男");
        infoSchool.setText("外国语学院");
        infoIdentify.setText("已认证");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onClick_Head:
                popWindowUtil.showBottom(pop_take_view, v);
                break;
            case R.id.onClick_Nick:
                startActivity(DemoActivity.class);
                break;
            case R.id.onClick_School:
                break;
            case R.id.onClick_Birthday:
                break;
            case R.id.onClick_Sex:
                break;
            case R.id.onClick_Identify:
                startActivity(AuthActivity.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePicUtil.onActivityResult(requestCode, resultCode, data);
        if (takePicUtil.getResultBitmap != null) {
            LoggerUtil.e("takePicUtil.getResultBitmap is full");
            handler.sendEmptyMessage(0);
        } else {
            LoggerUtil.e("takePicUtil.getResultBitmap is null");
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                loadCircle(takePicUtil.imageUri, infoHead);
            }
        }
    };


    private void loadCircle(Uri uri, ImageView imageView) {
        Glide.with(MyInfoActivity.this).load(uri).signature(new StringSignature(UUID.randomUUID().toString())).diskCacheStrategy(DiskCacheStrategy.NONE).bitmapTransform(new CircleTransform(MyInfoActivity.this)).into(imageView);
    }
}

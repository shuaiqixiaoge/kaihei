package com.xiaohui.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhy.baselib.activity.BaseApp;
import com.lhy.baselib.bean.response.PADBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.HttpUrlManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.SpUtils;
import com.xiaohui.android.activity.web.WebViewNoTitleActivity;
import com.xiaohui.android.constants.WebConstants;
import com.xiaohui.android.view.CircleTextProgressbar;

import butterknife.BindView;

import static com.xiaohui.android.R.id.iv_weimiao;

/**
 * @author zwy
 * @describe 启动页
 * @date 2017-4-24
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_red_skip)
    CircleTextProgressbar mTvSkip;
    @BindView(iv_weimiao)
    ImageView ivWeimiao;
    private String link;

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        HttpManager.getInstance().addObservable(getTimeData, HttpConstants.EventCode.TIME);
        SkipADPage();

        ivSplash.setOnClickListener(this);
    }


    @Override
    public int resLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        HttpManager.getInstance().addObservable(getADData, HttpConstants.EventCode.SPLASH_AD);
    }


    private ApiCallBack getADData = new ApiCallBack<PADBean>() {
        @Override
        public void onSuccess(PADBean data) {
            mTvSkip.setVisibility(View.VISIBLE);
            if (data.getIs_full().equals("0")) {//全屏
                ivWeimiao.setVisibility(View.GONE);
            } else {
                ivWeimiao.setVisibility(View.VISIBLE);
            }
            link = data.getLink();
            Glide.with(SplashActivity.this).load(HttpUrlManager.HOST_URL + data.getImg()).into(ivSplash);

        }

        @Override
        public void onFailure(int code, String msg) {
            mTvSkip.setVisibility(View.GONE);
            ivWeimiao.setVisibility(View.VISIBLE);
        }

        @Override
        public void onStartRequest() {

        }
    };
    private ApiCallBack getTimeData = new ApiCallBack<String>() {
        @Override
        public void onSuccess(String data) {
            if (data != null) {
                long time = System.currentTimeMillis() / 1000;
                long re_su = Long.parseLong(data) - time;
                SpUtils.putString(BaseApp.context, "sysTime", String.valueOf(re_su));
            } else {
                SpUtils.putString(BaseApp.context, "sysTime", "0");
            }
        }

        @Override
        public void onFailure(int code, String msg) {
        }

        @Override
        public void onStartRequest() {

        }
    };


    private void SkipADPage() {
        // 模拟网易新闻跳过。
        mTvSkip = (CircleTextProgressbar) findViewById(R.id.tv_red_skip);
        mTvSkip.setOutLineColor(Color.TRANSPARENT);
        mTvSkip.setInCircleColor(Color.parseColor("#3D000000"));
        mTvSkip.setProgressColor(Color.parseColor("#73a8ff"));
        mTvSkip.setProgressLineWidth(3);

        mTvSkip.setCountdownProgressListener(1, new CircleTextProgressbar.OnCountdownProgressListener() {
            @Override
            public void onProgress(int what, int progress) {

                if (what == 1 && progress == 0) {
//                    startOnActivity();
                    startActivity(MainActivity.class);
                    finish();
                }
            }
        });

        mTvSkip.reStart();

        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startOnActivity();
                startActivity(MainActivity.class);
                mTvSkip.stop();
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_splash:
                if (!TextUtils.isEmpty(link)) {
                    mTvSkip.stop();
                    Intent intent = new Intent(SplashActivity.this, WebViewNoTitleActivity.class);
                    intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_AD);
                    intent.putExtra(WebConstants.Key_Url, link);
                    intent.putExtra("spla", 5);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}

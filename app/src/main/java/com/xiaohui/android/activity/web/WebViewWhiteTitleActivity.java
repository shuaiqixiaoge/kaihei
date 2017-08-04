package com.xiaohui.android.activity.web;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.gyf.barlibrary.BarManager;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.constants.WebConstants;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewWhiteTitleActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.title)
    TextView title;

    private int code = -1;//要加载网页Code

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
        code = this.getIntent().getIntExtra(WebConstants.Key_Code, -1);
        initWebView();
    }

    private void initWebView() {
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAppCacheEnabled(true);
    }

    @OnClick(R.id.onClick_BACK)
    public void onClickBACK() {
        finish();
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_web_view_white_title;
    }

    @Override
    public void initData() {
        switch (code) {
            case WebConstants.CODE_PROTOCOL_ZX:
                title.setText("资讯");
                String url = this.getIntent().getStringExtra(WebConstants.Key_Url);
                webView.loadUrl(url);
                break;
        }
    }
}

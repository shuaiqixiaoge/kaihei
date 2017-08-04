package com.xiaohui.android.activity.web;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.constants.WebConstants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zwy
 * @describe webView加载页
 * @date 2017.5.10
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.title)
    TextView title;

    private int code = -1;//要加载网页Code

    @Override
    public void onActCreate(Bundle savedInstanceState) {
        initWebView();
        code = this.getIntent().getIntExtra(WebConstants.Key_Code, -1);
    }

    @OnClick(R.id.onClick_BACK)
    public void onClickBACK() {
        finish();
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

    @Override
    public int resLayoutID() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
        switch (code) {
            case WebConstants.CODE_PROTOCOL_SJ:
                title.setText("时见");
                webView.loadUrl(WebConstants.Build().buildLocalURL(WebConstants.URL_PROTOCOL_SJ).buildFinish());
                break;
            case WebConstants.CODE_PROTOCOL_SHARE:
                title.setText("分享赚积分");
                webView.loadUrl(WebConstants.Build().buildLocalURL(WebConstants.URL_PROTOCOL_SHARE).buildFinishUID());
                break;
        }
    }
}

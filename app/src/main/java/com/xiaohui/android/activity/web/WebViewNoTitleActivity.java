package com.xiaohui.android.activity.web;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gyf.barlibrary.BarManager;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;
import com.xiaohui.android.constants.WebConstants;

import butterknife.BindView;

/**
 * @author zwy
 * @describe webView无标题
 * @date 2017.5.10
 */
public class WebViewNoTitleActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;

    private int code = -1;

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
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
//        webView.addJavascriptInterface(new JsInterface(this), "Android");
    }

    @Override
    public int resLayoutID() {
        return R.layout.activity_web_view_no_title;
    }

    @Override
    public void initData() {
        switch (code) {
            case WebConstants.CODE_URL_WH:
                webView.loadUrl(WebConstants.Build().buildWebURL(WebConstants.URL_WH).buildFinishUID());
                break;
            case WebConstants.CODE_URL_JY:
                webView.loadUrl(WebConstants.Build().buildWebURL(WebConstants.URL_JY).buildFinishUID());
                break;
            case WebConstants.CODE_URL_MZX:
                webView.loadUrl(WebConstants.Build().buildWebURL(WebConstants.URL_MZX).buildFinishUID());
                break;
            case WebConstants.CODE_AD:
                String link = getIntent().getStringExtra(WebConstants.Key_Url);
                webView.loadUrl(link);
                break;
        }
    }


//    private class JsInterface {
//        private Context mContext;
//
//        public JsInterface(Context context) {
//            this.mContext = context;
//        }
//
//        @JavascriptInterface
//        public void closeBtnClick() {
//            finish();
//        }
//
//        @JavascriptInterface
//        public void openPersonDetail(final String fuid) {
//            if (!TextUtils.isEmpty(fuid)) {
//                Intent intent = new Intent(mContext, FriendsProfile3Activity.class);
//                intent.putExtra("fuid", fuid);
//                mContext.startActivity(intent);
//            }
//        }
//
//        //        校股--购买
//        @JavascriptInterface
//        public void goToBuy(String sid) {
//            if (UserStatus.isAuth(WebViewNoTitleActivity.this)) {
//                Intent intent = new Intent(mContext, BuySellActivity.class);
//                intent.putExtra("tab", 0);
//                intent.putExtra("sid", sid);
//                mContext.startActivity(intent);
//            } else {
//                Intent intent = new Intent(WebViewNoTitleActivity.this, AuthActivity.class);
//                startActivity(intent);
//                Toast.makeText(WebViewNoTitleActivity.this, "请完善身份认证信息", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        //        校股--转让
//        @JavascriptInterface
//        public void goMakeOver(String sid) {
//            if (UserStatus.isAuth(WebViewNoTitleActivity.this)) {
//                Intent intent = new Intent(mContext, BuySellActivity.class);
//                intent.putExtra("tab", 1);
//                intent.putExtra("sid", sid);
//                mContext.startActivity(intent);
//            } else {
//                Intent intent = new Intent(mContext, AuthActivity.class);
//                startActivity(intent);
//                Toast.makeText(mContext, "请完善身份认证信息", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        //        校股-------分享
//        @JavascriptInterface
//        public void goShare(String sid) {
//            if (!TextUtils.isEmpty(sid)) {
//                showShareDialog(sid);
//            }
//        }
//    }
//
//    private void showShareDialog(final String sid) {
//
//    }

//    private UMShareListener umShareListener = new UMShareListener() {
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(WebVActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(WebVActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(WebVActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
//            if (t != null) {
//            }
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(WebVActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
//        }
//    };
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }


}

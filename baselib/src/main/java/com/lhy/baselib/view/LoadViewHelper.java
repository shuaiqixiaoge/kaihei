package com.lhy.baselib.view;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lhy.baselib.R;
import com.shell.view.helper.IVaryViewHelper;
import com.shell.view.helper.VaryViewHelper;

/**
 * 自定义要切换的布局，通过IVaryViewHelper实现真正的切换<br>
 * 使用者可以根据自己的需求，使用自己定义的布局样式
 */
public class LoadViewHelper {

    private IVaryViewHelper helper;
    private View.OnClickListener retryListener;

    private final View.OnClickListener mInnerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (retryListener != null) {
                retryListener.onClick(v);
            }
        }
    };

    public LoadViewHelper(View view) {
        this(new VaryViewHelper(view));
    }

    public void setRetryListener(View.OnClickListener retryListener) {
        this.retryListener = retryListener;
    }


    public LoadViewHelper(View view, View.OnClickListener retryListener) {
        this(new VaryViewHelper(view), retryListener);
    }

    public LoadViewHelper(IVaryViewHelper helper, View.OnClickListener retryListener) {
        super();
        this.helper = helper;
        this.retryListener = retryListener;
    }

    public LoadViewHelper(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showLoading() {
        SuccinctProgress.showSuccinctProgress(helper.getContext(), "正在加载...", SuccinctProgress.THEME_LINE, false, false);
    }

    public void showError() {
        showError(null);
    }

    public void showError(@StringRes int errorText) {
        showError(helper.getContext().getString(errorText));
    }

    public void showError(String errorText) {
        View layout = helper.inflate(R.layout.layout_load_error);
        TextView textView = (TextView) layout.findViewById(R.id.tv_load_error);
        if (!TextUtils.isEmpty(errorText))
            textView.setText(errorText);
        layout.setOnClickListener(mInnerListener);
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }
}
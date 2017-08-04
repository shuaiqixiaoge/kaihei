package com.lhy.baselib.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.lhy.baselib.R;

public class WaitingDialog extends ProgressDialog {

    public WaitingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage("正在加载...");
    }

    public WaitingDialog(Context context, int theme) {
        super(context, theme);
    }

}
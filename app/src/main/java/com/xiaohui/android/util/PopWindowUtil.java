package com.xiaohui.android.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.lhy.baselib.utils.DensityUtil;
import com.lhy.baselib.utils.LoggerUtil;

/**
 * Created by zwy on 2017/6/1.
 * package_name is com.xiaohui.android.util
 * 描述:popWindow工具类
 */

public class PopWindowUtil {
    private Context context;
    private PopupWindow popupWindow;

    public PopWindowUtil(Context context) {
        this.context = context;
    }

    /**
     * 显示弹出框
     */
    public void showPopNoParent(final View convertView, final View parentView) {
        popupWindow = new PopupWindow(convertView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        int width = parentView.getWidth();
        popupWindow.setWidth(width);
        popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        popupWindow.setFocusable(false);
        popupWindow.showAsDropDown(parentView);
    }


    public void showBottom(final View convertView, final View parentView) {
        popupWindow = new PopupWindow(convertView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
        popupWindow.setFocusable(false);
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }


    public void showPopParent(final View convertView, final View parentView) {
        ViewTreeObserver vto2 = parentView.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = parentView.getWidth();
                LoggerUtil.e("Width==" + width);
                popupWindow = new PopupWindow(convertView, width, DensityUtil.dip2px(context, 193));
                popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
                popupWindow.setFocusable(false);
                popupWindow.showAsDropDown(parentView);
            }
        });

    }


    /**
     * 关闭弹出框
     */
    public void dissmissPop() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}

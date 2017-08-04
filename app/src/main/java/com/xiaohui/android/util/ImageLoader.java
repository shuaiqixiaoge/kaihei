package com.xiaohui.android.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lhy.baselib.meta.CircleTransform;
import com.lhy.baselib.network.HttpUrlManager;
import com.xiaohui.android.R;

/**
 * Created by zwy on 2017/5/26.
 * package_name is com.xiaohui.android.util
 * 描述:图片加载
 */

public class ImageLoader {
    private static ImageLoader instance;
    private RequestManager glide;
    private Context context;

    public static ImageLoader getInstance(Context context) {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader(context);
                }
            }
        }
        return instance;
    }

    public ImageLoader(Context context) {
        glide = Glide.with(context);
        this.context = context;
    }

    /**
     * 加载圆形图片
     *
     * @param uri
     * @param imageView
     */
    public void loadCircleImg(Uri uri, ImageView imageView) {
        if (uri != null) {
            loadDefaultCircleImg(imageView);
            return;
        }
        loadCircle(uri, imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param uri
     * @param imageView
     */
    public void loadCircleHead(Uri uri, ImageView imageView) {
        if (uri == null) {
            loadDefaultCircleHead(imageView);
            return;
        }
        loadCircle(uri, imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param url
     * @param imageView
     */
    public void loadCircleImg(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultCircleImg(imageView);
            return;
        }
        loadCircle(url, imageView);
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void loadImg(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultImg(imageView);
            return;
        }
        load(url, imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param url
     * @param imageView
     */
    public void loadCircleHead(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultCircleHead(imageView);
            return;
        }
        loadCircle(url, imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param resId
     * @param imageView
     */
    public void loadCircleHead(int resId, ImageView imageView) {
        if (resId == 0) {
            loadDefaultCircleHead(imageView);
            return;
        }
        loadCircle(resId, imageView);
    }

    /**
     * 加载头像
     *
     * @param url
     * @param imageView
     */
    public void loadHead(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            loadDefaultHead(imageView);
            return;
        }
        load(url, imageView);
    }

    /**
     * 加载默认圆形头像
     *
     * @param imageView
     */
    private void loadDefaultCircleHead(ImageView imageView) {
        glide.load(R.drawable.splash_bg).bitmapTransform(new CircleTransform(context)).into(imageView);
    }

    /**
     * 加载默认头像
     *
     * @param imageView
     */
    private void loadDefaultHead(ImageView imageView) {
        glide.load(R.drawable.splash_bg).into(imageView);
    }

    /**
     * 加载默认圆形图片
     *
     * @param imageView
     */
    private void loadDefaultCircleImg(ImageView imageView) {
        glide.load(R.drawable.splash_bg).bitmapTransform(new CircleTransform(context)).into(imageView);
    }

    /**
     * 加载默认图像
     *
     * @param imageView
     */
    private void loadDefaultImg(ImageView imageView) {
        glide.load(R.drawable.splash_bg).into(imageView);
    }

    /**
     * 加载图像
     *
     * @param url
     * @param imageView
     */
    private void load(String url, ImageView imageView) {
        if (url.contains("http://")) {
            glide.load(url).into(imageView);
        } else {
            glide.load(HttpUrlManager.getImg(url)).into(imageView);
        }
    }

    /**
     * 加载圆形图像
     *
     * @param url
     * @param imageView
     */
    private void loadCircle(String url, ImageView imageView) {
        if (url.contains("http://")) {
            glide.load(url).bitmapTransform(new CircleTransform(context)).into(imageView);
        } else {
            glide.load(HttpUrlManager.getImg(url)).bitmapTransform(new CircleTransform(context)).into(imageView);
        }
    }

    /**
     * 加载圆形图像
     *
     * @param resId
     * @param imageView
     */
    private void loadCircle(int resId, ImageView imageView) {
        glide.load(resId).bitmapTransform(new CircleTransform(context)).into(imageView);
    }


    /**
     * 加载圆形图像
     *
     * @param imageView
     */

    private void loadCircle(Uri uri, ImageView imageView) {
        glide.load(uri).bitmapTransform(new CircleTransform(context)).into(imageView);
    }


}

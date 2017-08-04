package com.lhy.baselib.utils;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/30.
 */

public class BitmapUtil {
    // 压缩图片url
    public Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是1280*720分辨率，所以高和宽我们设置为
        float hh = 1280f;// 这里设置高度为1280f
        float ww = 720f;// 这里设置宽度为720f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * @param @param  bitmap
     * @param @return 设定文件
     * @return Drawable 返回类型
     * @Title: bitmapToDrawable
     * @Description: TODO
     */
    @SuppressWarnings("deprecation")
    public Drawable bitmapToDrawable(Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    /**
     * @param @param  drawable
     * @param @return 设定文件
     * @return Bitmap 返回类型
     * @Title: drawableToDrawable
     * @Description: TODO
     */
    public Bitmap drawableToDrawable(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * @param @param  res
     * @param @param  resourseid
     * @param @return 设定文件
     * @return Bitmap 返回类型
     * @Title: ResDrawableToBitmap
     * @Description: TODO
     */
    public Bitmap ResDrawableToBitmap(Resources res, int resource_Id) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, resource_Id);
        return bitmap;
    }

    /**
     * 根据URL获取Bitmap
     */
    public Bitmap getHttpBitmap(String url) {
        Bitmap bitmap = null;
        URL myUrl;
        try {
            myUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            // 把bitmap转成圆形
            bitmap = toRoundBitmap(bitmap);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 返回圆形bitmap
        return bitmap;
    }

    /**
     * 把bitmap转成圆形
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r = 0;
        // 取最短边做边长
        if (width < height) {
            r = width;
        } else {
            r = height;
        }
        // 构建一个bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBm);
        Paint p = new Paint();
        // 设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        // 且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, p);
        // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }

    /**
     * @param bm
     * @return byte[]
     * @Title: Bitmap2Bytes
     * @describe:Bitmap转换成byte[]
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream bao_s = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bao_s);
        return bao_s.toByteArray();
    }

    /**
     * @param bm
     * @return byte[]
     * @Title: Bitmap2Bytes
     * @describe:Bitmap转换成byte[]
     */

    public File Bitmap2File(Bitmap bm, String pake_name) {
        /** 我的内存卡文件存放路径 **/
        String sd_my_file = Environment.getExternalStorageDirectory() + "/android/data/" + pake_name;
        Random random = new Random();
        String bitName = "a" + random.nextInt(1000);
        File f = new File(sd_my_file + "/" + bitName + ".png");
        FileOutputStream fos = null;
        BufferedOutputStream bo_s = null;
        ByteArrayOutputStream bao_s = null; // 字节数组输出流
        try {
            bao_s = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bao_s);
            byte[] bytes = bao_s.toByteArray();
            fos = new FileOutputStream(f);
            bo_s = new BufferedOutputStream(fos);
            bo_s.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bao_s != null) {
                try {
                    bao_s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bo_s != null) {
                try {
                    bo_s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return f;
    }


}

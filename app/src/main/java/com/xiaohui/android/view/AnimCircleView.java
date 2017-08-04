package com.xiaohui.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaohui.android.R;

/**
 * Created by zwy on 2017/6/15.
 * package_name is com.xiaohui.android.view
 * 描述:XH2
 */

public class AnimCircleView extends View {
    private int firstSize;
    private int firstColor;
    private int firstSpeed;
    private int secondSize;
    private int secondColor;
    private int secondSpeed;
    private int centerSize;
    private int centerColor;
    private String centerText;

    private Paint mPaint;
    private RectF rectf;
    private int progress;//当前进度
    private boolean isNext = false;//是否开始下一个


    public AnimCircleView(Context context) {
        this(context, null);
    }

    public AnimCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AnimTestView, defStyleAttr, 0);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.AnimCircleView_firstSize:
                    firstSize = array.getDimensionPixelSize(attr, 20);
                    break;
                case R.styleable.AnimCircleView_firstColor:
                    firstColor = array.getColor(attr, Color.YELLOW);
                    break;
                case R.styleable.AnimCircleView_secondSize:
                    secondSize = array.getDimensionPixelSize(attr, 25);
                    break;
                case R.styleable.AnimCircleView_secondColor:
                    secondColor = array.getColor(attr, Color.RED);
                    break;
                case R.styleable.AnimCircleView_centerSize:
                    centerSize = array.getDimensionPixelSize(attr, 15);
                    break;
                case R.styleable.AnimCircleView_centerColor:
                    centerColor = array.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.AnimCircleView_centerText:
                    centerText = array.getString(attr);
                    break;
                case R.styleable.AnimCircleView_firstSpeed:
                    firstSpeed = array.getInteger(attr, 0);
                    break;
                case R.styleable.AnimCircleView_secondSpeed:
                    secondSpeed = array.getInteger(attr, 0);
                    break;
            }
        }
        array.recycle();

        rectf = new RectF();
        mPaint = new Paint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    progress++;
                    if (progress == 360) {
                        progress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    postInvalidate();
                    try {
                        if (isNext) {
                            Thread.sleep(firstSpeed);
                        } else {
                            Thread.sleep(secondSpeed);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2; // 获取圆心的X坐标
        int radius = center - firstSize;//半径
        mPaint.setStrokeWidth(firstSize);//设置圆环的宽度
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        rectf.left = center - radius;
        rectf.top = center - radius;
        rectf.right = center + radius;
        rectf.bottom = center + radius;

        if (!isNext) {
            mPaint.setColor(firstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(secondColor);
            canvas.drawArc(rectf, -90, progress, false, mPaint);
            mPaint.setColor(centerColor);
            mPaint.setTextSize(centerSize);
            canvas.drawText(centerText, getWidth() / 2, getHeight() / 2, mPaint);
        } else {
            mPaint.setColor(secondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(firstColor);
            canvas.drawArc(rectf, -90, progress, false, mPaint);
            mPaint.setColor(centerColor);
            mPaint.setTextSize(centerSize);
            canvas.drawText(centerText, getWidth() / 2, getHeight() / 2, mPaint);
        }
    }
}

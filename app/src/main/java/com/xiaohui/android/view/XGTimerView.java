package com.xiaohui.android.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaohui.android.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Amy on 2016/7/21.
 */
public class XGTimerView extends LinearLayout {


    //    private final TextView tv_day_decade;
//    private final TextView tv_day_unit;
    private final TextView tv_min_decade;
    private final TextView tv_min_unit;
    private final TextView tv_sec_decade;
    private final TextView tv_sec_unit;
    private final TextView tv_hour_unit;
    private final TextView tv_hour_decade;
    // 计时器
    private Timer timer;
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            countDown();
        }

        ;
    };
    private long day = 0;
    private long hour = 0;
    private long min = 0;
    private long sec = 0;
    private long day_decade;
    private long day_unit;

    private long hour_decade;
    private long hour_unit;
    private long min_decade;
    private long min_unit;
    private long sec_decade;
    private long sec_unit;

    public XGTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_xg_view_timer, this);
        tv_hour_decade = (TextView) view.findViewById(R.id.tv_hour_decade);
        tv_hour_unit = (TextView) view.findViewById(R.id.tv_hour_unit);
//        tv_day_decade=(TextView)view.findViewById(R.id.tv_day_decade);
//        tv_day_unit=(TextView)view.findViewById(R.id.tv_day_unit);
        tv_min_decade = (TextView) view.findViewById(R.id.tv_min_decade);
        tv_min_unit = (TextView) view.findViewById(R.id.tv_min_unit);
        tv_sec_decade = (TextView) view.findViewById(R.id.tv_sec_decade);
        tv_sec_unit = (TextView) view.findViewById(R.id.tv_sec_unit);

    }

    /*
    * 开始计时
    *
    * */
    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }

    /*
    * 停止计时
    * */
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /*
    * 如果sum=12345678
    * */
    public void addTime(long sum) {
        long sec = sum % 60;
        // 如果大于60秒，获取分钟。（秒数）
        long sec_time = sum / 60;
        // 再获取分钟
        long min = sec_time % 60;
        // 如果大于60分钟，获取小时（分钟数）。
        long min_time = sec_time / 60;

        setTime(min_time, min, sec);
    }

    /*
    * 计算倒计时的时长
    * */
    public void setTime(long hour, long min, long sec) {
        if (min >= 60 || sec >= 60
                || hour < 0 || min < 0 || sec < 0) {
            throw new RuntimeException(
                    "Time format is error,please check out your code");
        }

        hour_decade = hour / 10;
        hour_unit = hour - hour_decade * 10;

        min_decade = min / 10;
        min_unit = min - min_decade * 10;

        sec_decade = sec / 10;
        sec_unit = sec - sec_decade * 10;
        // 第个time 进行初始化
        timeClean();

    }

    private OnTimeStopListener onTimeStopListener;

    public void setOnTimeStopListener(OnTimeStopListener onTimeStopListener) {
        this.onTimeStopListener = onTimeStopListener;
    }


    private void timeClean() {
//        tv_day_decade.setText(day_decade+"");
//        tv_day_unit.setText(day_unit+"");
        tv_hour_decade.setText(hour_decade + "");
        tv_hour_unit.setText(hour_unit + "");
        tv_min_decade.setText(min_decade + "");
        tv_min_unit.setText(min_unit + "");
        tv_sec_decade.setText(sec_decade + "");
        tv_sec_unit.setText(sec_unit + "");


    }
    /*
    * 作倒计时的操作
    * */

    public Boolean countDown() {

        if (isCarry4Unit(tv_sec_unit)) {
            if (isCarry4Decade(tv_sec_decade)) {

                if (isCarry4Unit(tv_min_unit)) {
                    if (isCarry4Decade(tv_min_decade)) {

                        if (isDay4Unit(tv_hour_unit)) {
                            if (isDay4Decade(tv_hour_decade)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("时间到了");
                                builder.show();
                                tv_hour_decade.setText("0");
                                tv_hour_unit.setText("0");
                                tv_min_decade.setText("0");
                                tv_min_unit.setText("0");
                                tv_sec_decade.setText("0");
                                tv_sec_unit.setText("0");
                                onTimeStopListener.onTimeStopListener();
                                stop();
                                return false;

                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public interface OnTimeStopListener {
        void onTimeStopListener();
    }


    /**
     * 进行——时分秒，判断个位数
     *
     * @param
     * @return boolean
     * @throws
     * @Description: 变化十位，并判断是否需要进位
     */
    private boolean isCarry4Decade(TextView tv) {

        long time = Long.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @param
     * @return boolean
     * @throws
     * @Description: 变化个位，并判断是否需要进位
     */
    private boolean isCarry4Unit(TextView tv) {

        long time = Long.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @param
     * @return boolean
     * @throws
     * @Description: 变化十位，并判断是否需要进位
     */
    private boolean isDay4Unit(TextView tv) {

        long time = Long.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 3;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }

    /**
     * 进行——时分秒，判断个位数
     *
     * @param
     * @return boolean
     * @throws
     * @Description: 变化个位，并判断是否需要进位
     */
    private boolean isDay4Decade(TextView tv) {

        long time = Long.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 2;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }

    }


}
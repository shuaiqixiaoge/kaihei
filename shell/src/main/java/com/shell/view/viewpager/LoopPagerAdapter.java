package com.shell.view.viewpager;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
/**
 * Created by Mr.Jude on 2016/1/9.
 */
public abstract class LoopPagerAdapter extends PagerAdapter{
    private ViewPager mViewPager;

    private ArrayList<View> mViewList = new ArrayList<>();

    @Override
    public void notifyDataSetChanged() {
        mViewList.clear();
        initPosition();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        initPosition();
    }

    private void initPosition(){
        if (mViewPager.getCurrentItem() == 0&&getRealCount()>0){
            int half = Integer.MAX_VALUE/2;
            int start = half - half%getRealCount();
            setCurrent(start);
        }
    }

    private void setCurrent(int index){
        try {
            Field field = ViewPager.class.getDeclaredField("mCurItem");
            field.setAccessible(true);
            field.set(mViewPager,index);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public LoopPagerAdapter(ViewPager viewPager){
        this.mViewPager = viewPager;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = position%getRealCount();
        View itemView = findViewByPosition(container,realPosition);
        container.addView(itemView);
        return itemView;
    }


    private View findViewByPosition(ViewGroup container,int position){
        for (View view : mViewList) {
            if (((int)view.getTag()) == position&&view.getParent()==null){
                return view;
            }
        }
        View view = getView(container,position);
        view.setTag(position);
        mViewList.add(view);
        return view;
    }

    public abstract View getView(ViewGroup container, int position);

    @Deprecated
    @Override
    public final int getCount() {
        return getRealCount()<=0?getRealCount():Integer.MAX_VALUE;
    }

    public abstract int getRealCount();
}
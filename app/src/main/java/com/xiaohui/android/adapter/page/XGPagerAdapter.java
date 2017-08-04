package com.xiaohui.android.adapter.page;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.xiaohui.android.fragment.xg.XGBaseItemFragment;

import java.util.ArrayList;
import java.util.List;

public class XGPagerAdapter extends FragmentStatePagerAdapter {

    private final List<XGBaseItemFragment> mFragments = new ArrayList<>();//添加的Fragment的集合
    private final List<String> mFragmentsTitles = new ArrayList<>();//每个Fragment对应的title的集合

    public XGPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment      添加Fragment
     * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
     */
    public void addFragment(XGBaseItemFragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitles.add(fragmentTitle);
    }

    @Override
    public XGBaseItemFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitles.get(position);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }
}
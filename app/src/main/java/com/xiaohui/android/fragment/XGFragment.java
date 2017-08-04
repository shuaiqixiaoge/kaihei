package com.xiaohui.android.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhy.baselib.bean.response.PXGTypeBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.SpUtils;
import com.lhy.baselib.view.SuccinctProgress;
import com.xiaohui.android.R;
import com.xiaohui.android.adapter.page.XGPagerAdapter;
import com.xiaohui.android.fragment.xg.XGItemFragment;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link XGFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class XGFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.onClick_DEAL)
    ImageView onClickDEAL;
    @BindView(R.id.onClick_SEARCH)
    ImageView onClickSEARCH;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;

    private XGPagerAdapter adapter;


    private String price_type = "0";
    private String gap_type = "0";

    private int position = 1;//保存位置
    private String uid;

    private List<PXGTypeBean> list;

    public XGFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment XGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static XGFragment newInstance() {
        XGFragment fragment = new XGFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SpUtils.getUid(getActivity());
        HttpManager.getInstance().addObservable(xg_callback, HttpConstants.EventCode.XG_USER_TYPE);
    }

    public ApiCallBack<List<PXGTypeBean>> xg_callback = new ApiCallBack<List<PXGTypeBean>>() {
        @Override
        public void onSuccess(List<PXGTypeBean> data) {
            initTabLayout(data);
        }

        @Override
        public void onFailure(int code, String msg) {

        }

        @Override
        public void onStartRequest() {
            SuccinctProgress.showSuccinctProgress(getActivity(), "正在加载", SuccinctProgress.THEME_LINE, false, false);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xg, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSaveInstanceState(savedInstanceState);
        onClickDEAL.setOnClickListener(this);
        onClickSEARCH.setOnClickListener(this);
    }

    /**
     * 初始化保存的装态
     *
     * @param savedInstanceState
     */
    private void initSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
            viewPager.setCurrentItem(position);
            String content = tabLayout.getTabAt(position).getText().toString();
            tabLayout.getTabAt(position).setText(Html.fromHtml("<big><font color='#2270f1'>" + content + "</font><big>"));
            tabLayout.getTabAt(position).select();
            tabLayout.performClick();
            adapter.getItem(position).setRefresh(uid, list.get(position).getId(), price_type, gap_type);
        }
    }


    /**
     * 初始化TabLayout
     */
    @SuppressLint("NewApi")
    private void initTabLayout(final List<PXGTypeBean> list) {
        this.list = list;
        adapter = new XGPagerAdapter(getChildFragmentManager());
        for (int i = 0; i < list.size(); i++) {
            adapter.addFragment(XGItemFragment.newInstance(list.get(i).getId()), list.get(i).getName());//添加Fragment
            tabLayout.addTab(tabLayout.newTab().setText(list.get(i).getName()));//给TabLayout添加Tab
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position);
        String content = tabLayout.getTabAt(position).getText().toString();
        tabLayout.getTabAt(position).setText(Html.fromHtml("<big><font color='#2270f1'>" + content + "</font><big>"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String content = tab.getText().toString();
                tab.setText(Html.fromHtml("<big><font color='#2270f1'>" + content + "</font><big>"));
                position = tab.getPosition();
                adapter.getItem(tab.getPosition()).setRefresh(uid, list.get(position).getId(), price_type, gap_type);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                String content = tab.getText().toString();
                tab.setText(Html.fromHtml("<font color='#4e5883'>" + content + "</font>"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        tabLayout.getTabAt(position).select();
        tabLayout.performClick();

        adapter.getItem(position).setRefresh(uid, list.get(position).getId(), price_type, gap_type);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onClick_DEAL:
                break;
            case R.id.onClick_SEARCH:
                break;
        }
    }
}

package com.xiaohui.android.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.lhy.baselib.bean.RUserIdBean;
import com.lhy.baselib.bean.requestparams.RZXBean;
import com.lhy.baselib.bean.response.PZxBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.HttpUrlManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.LoggerUtil;
import com.lhy.baselib.utils.SpUtils;
import com.lhy.baselib.view.SuccinctProgress;
import com.shell.view.recyclerview.adapter.BaseMultiItemQuickAdapter;
import com.shell.view.refresh.RefreshListenerAdapter;
import com.shell.view.refresh.TwinklingRefreshLayout;
import com.shell.view.refresh.header.ClassicRefreshView;
import com.xiaohui.android.R;
import com.xiaohui.android.adapter.recycle.ZxAdapter;

import java.util.List;

/**
 * 资讯
 */
public class ZXFragment extends Fragment {
    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private View headView;

    private ConvenientBanner<PZxBean.BannerListBean> banner;
    private BaseMultiItemQuickAdapter<PZxBean.InformationListBean> adapter;

    private int page = 1;
    private String uid;


    public ZXFragment() {
    }

    public static ZXFragment newInstance() {
        ZXFragment fragment = new ZXFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SpUtils.getUid(getActivity());
        HttpManager.getInstance().addObservable(zx_callback, HttpConstants.EventCode.ZX, new RZXBean(uid, page));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zx, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout = (TwinklingRefreshLayout) getView().findViewById(R.id.swipe_layout);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycleView);
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zx_head, null);
        banner = (ConvenientBanner) headView.findViewById(R.id.banner);
        initRefreshLayout();
        initRecycleView();
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        adapter = new BaseMultiItemQuickAdapter();
        adapter.addHeaderView(headView);
        adapter.setItemDefaultType(new ZxAdapter());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 获取资讯信息
     */
    public ApiCallBack<PZxBean> zx_callback = new ApiCallBack<PZxBean>() {
        @Override
        public void onSuccess(PZxBean data) {
            refreshLayout.finishRefreshing();
            LoggerUtil.e(data.getBanner_list().size() + "=====" + data.getInformation_list().size());
            initBanner(data.getBanner_list());
            if (page == 1) {
                adapter.setNewData(data.getInformation_list());
            } else {
                if (data.getInformation_list().size() < 10) {
                    refreshLayout.setEnableLoadMore(false);
                }
                adapter.addData(data.getInformation_list());
            }
        }

        @Override
        public void onFailure(int code, String msg) {
            refreshLayout.finishRefreshing();
            Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onStartRequest() {
            SuccinctProgress.showSuccinctProgress(getActivity(), "正在加载...", SuccinctProgress.THEME_LINE, false, false);
        }
    };


    /**
     * 初始化刷新控件
     */
    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setHeaderView(new ClassicRefreshView(getActivity()));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                page = 1;
                HttpManager.getInstance().addObservable(zx_callback, HttpConstants.EventCode.ZX, new RZXBean(uid, page));
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                page++;
                HttpManager.getInstance().addObservable(zx_callback, HttpConstants.EventCode.ZX, new RZXBean(uid, page));
            }
        });
    }

    /**
     * 加载Banner轮播图
     *
     * @param data
     */
    private void initBanner(List<PZxBean.BannerListBean> data) {
        banner.setPages(new CBViewHolderCreator<ImageHolderView>() {
            @Override
            public ImageHolderView createHolder() {
                return new ImageHolderView();
            }
        }, data)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置翻页的效果，不需要翻页效果可用不设
                .setPageTransformer(new AccordionTransformer());
    }

    private class ImageHolderView implements Holder<PZxBean.BannerListBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, PZxBean.BannerListBean data) {
            Glide.with(getActivity()).load(HttpUrlManager.HOST_URL + data.getImg()).into(imageView);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopTurning();
    }

}

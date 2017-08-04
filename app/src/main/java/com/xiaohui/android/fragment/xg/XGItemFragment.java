package com.xiaohui.android.fragment.xg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhy.baselib.bean.requestparams.RXGItemBean;
import com.lhy.baselib.bean.response.PXGMainBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.LoggerUtil;
import com.lhy.baselib.utils.SpUtils;
import com.shell.view.recyclerview.adapter.BaseMultiItemQuickAdapter;
import com.shell.view.refresh.RefreshListenerAdapter;
import com.shell.view.refresh.TwinklingRefreshLayout;
import com.shell.view.refresh.header.ClassicRefreshView;
import com.xiaohui.android.R;
import com.xiaohui.android.adapter.recycle.XGAdapter;

import java.util.List;

public class XGItemFragment extends XGBaseItemFragment {
    private static final String type_id_key = "type_id";

    private String type_id;//用户类型
    private int page = 1;
    private String new_price_sort = "0";//0不排序,1正序,2倒序
    private String fluctua_range_sort = "0";//0不排序,1正序,2倒序

    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private BaseMultiItemQuickAdapter<PXGMainBean> adapter;

    private String uid;

    public XGItemFragment() {
    }

    public static XGItemFragment newInstance(String type_id) {
        XGItemFragment fragment = new XGItemFragment();
        Bundle args = new Bundle();
        args.putString(type_id_key, type_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SpUtils.getUid(getActivity());
        if (getArguments() != null) {
            type_id = getArguments().getString(type_id_key);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xgmain, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout = (TwinklingRefreshLayout) getView().findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycleView);

        initRefreshLayout();
        initRecycleView();
    }


    public ApiCallBack<List<PXGMainBean>> callBack = new ApiCallBack<List<PXGMainBean>>() {
        @Override
        public void onSuccess(List<PXGMainBean> data) {
            refreshLayout.finishRefreshing();
            if (page == 1) {
                adapter.setNewData(data);
            } else {
                if (data.size() < 10) {
                    refreshLayout.setEnableLoadMore(false);
                }
                adapter.addData(data);
            }
        }

        @Override
        public void onFailure(int code, String msg) {
            if (refreshLayout != null && refreshLayout.isRefreshing()) {
                refreshLayout.finishRefreshing();
            }
            Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onStartRequest() {
        }
    };


    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        adapter = new BaseMultiItemQuickAdapter();
        adapter.setItemDefaultType(new XGAdapter());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

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
                HttpManager.getInstance().addObservable(callBack, HttpConstants.EventCode.XG_ITEM, new RXGItemBean(uid, page, type_id, new_price_sort, fluctua_range_sort));
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                page++;
                HttpManager.getInstance().addObservable(callBack, HttpConstants.EventCode.XG_ITEM, new RXGItemBean(uid, page, type_id, new_price_sort, fluctua_range_sort));
            }
        });
    }


    /**
     * 刷新方法
     *
     * @param price_type
     * @param gap_type
     */
    @Override
    public void setRefresh(String uid, String user_type, String price_type, String gap_type) {
        this.uid = uid;
        this.type_id = user_type;
        this.new_price_sort = price_type;
        this.fluctua_range_sort = gap_type;
        HttpManager.getInstance().addObservable(callBack, HttpConstants.EventCode.XG_ITEM, new RXGItemBean(uid, page, type_id, new_price_sort, fluctua_range_sort));
    }
}

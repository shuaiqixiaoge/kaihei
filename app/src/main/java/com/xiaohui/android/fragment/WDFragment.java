package com.xiaohui.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lhy.baselib.bean.RUserIdBean;
import com.lhy.baselib.bean.response.PWDDetailBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.SpUtils;
import com.xiaohui.android.R;
import com.xiaohui.android.activity.wd.MyInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WDFragment extends Fragment implements View.OnClickListener, ApiCallBack<PWDDetailBean> {
    @BindView(R.id.rl_wd_account)
    RelativeLayout rlWdAccount;
    @BindView(R.id.rl_wd_xiaotao)
    RelativeLayout rlWdXiaotao;
    @BindView(R.id.rl_wd_issuer)
    RelativeLayout rlWdIssuer;
    @BindView(R.id.rl_wd_sign)
    RelativeLayout rlWdSign;
    @BindView(R.id.rl_wd_service)
    RelativeLayout rlWdService;
    @BindView(R.id.rl_wd_question)
    RelativeLayout rlWdQuestion;
    @BindView(R.id.rl_wd_setting)
    RelativeLayout rlWdSetting;

    @BindView(R.id.avatar)
    ImageView avatar;
    Unbinder unbinder;


    private String uid;


    public WDFragment() {
    }

    public static WDFragment newInstance() {
        WDFragment fragment = new WDFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SpUtils.getUid(getActivity());
        HttpManager.getInstance().addObservable(this, HttpConstants.EventCode.WD_DETAIL, new RUserIdBean(uid));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wd, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnclick();
    }

    private void setOnclick() {
        rlWdAccount.setOnClickListener(this);
        rlWdXiaotao.setOnClickListener(this);
        rlWdIssuer.setOnClickListener(this);
        rlWdSign.setOnClickListener(this);
        rlWdService.setOnClickListener(this);
        rlWdQuestion.setOnClickListener(this);
        rlWdSetting.setOnClickListener(this);

        avatar.setOnClickListener(OnClick_Head);
    }

    /**
     * 头像点击
     */
    public View.OnClickListener OnClick_Head = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MyInfoActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.rl_wd_account://我的账户

                break;
            case R.id.rl_wd_xiaotao://我的校淘

                break;
            case R.id.rl_wd_issuer://我的发行人

                break;
            case R.id.rl_wd_sign://签约服务

                break;
            case R.id.rl_wd_service://客服

                break;
            case R.id.rl_wd_setting://设置

                break;
            case R.id.rl_wd_question://问题

                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(PWDDetailBean data) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }

    @Override
    public void onStartRequest() {

    }
}
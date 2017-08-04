package com.xiaohui.android.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lhy.baselib.bean.RUserIdBean;
import com.lhy.baselib.bean.response.PRedMessageBean;
import com.lhy.baselib.network.HttpConstants;
import com.lhy.baselib.network.HttpManager;
import com.lhy.baselib.network.iml.ApiCallBack;
import com.lhy.baselib.utils.ConstantsUtil;
import com.lhy.baselib.utils.SpUtils;
import com.xiaohui.android.R;
import com.xiaohui.android.activity.AuthActivity;
import com.xiaohui.android.activity.fx.EasyActivity;
import com.xiaohui.android.activity.fx.XTActivity;
import com.xiaohui.android.activity.web.WebViewActivity;
import com.xiaohui.android.activity.web.WebViewNoTitleActivity;
import com.xiaohui.android.constants.UserStatus;
import com.xiaohui.android.constants.WebConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FXFragment extends Fragment implements View.OnClickListener, ApiCallBack<PRedMessageBean> {
    @BindView(R.id.onClick_SJ)
    LinearLayout onClickSJ;
    @BindView(R.id.onClick_DT)
    RelativeLayout onClickDT;
    @BindView(R.id.onClick_EASY)
    RelativeLayout onClickEASY;
    @BindView(R.id.onClick_XH)
    RelativeLayout onClickXH;
    @BindView(R.id.onClick_WH)
    RelativeLayout onClickWH;
    @BindView(R.id.onClick_JF)
    RelativeLayout onClickJF;
    @BindView(R.id.onClick_SHARE)
    RelativeLayout onClickSHARE;
    @BindView(R.id.onClick_TEACH)
    RelativeLayout onClickTEACH;
    @BindView(R.id.onClick_MZX)
    RelativeLayout onClickMZX;
    @BindView(R.id.fx_message_show)
    ImageView fxMessageShow;


    private Unbinder unbinder;
    private String uid;

    public FXFragment() {
    }

    public static FXFragment newInstance() {
        FXFragment fragment = new FXFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid = SpUtils.getUid(getActivity());
        HttpManager.getInstance().addObservable(this, HttpConstants.EventCode.FX_MESSAGE_SHOW, new RUserIdBean(uid));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fx, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickSJ.setOnClickListener(this);
        onClickDT.setOnClickListener(this);
        onClickEASY.setOnClickListener(this);
        onClickXH.setOnClickListener(this);
        onClickWH.setOnClickListener(this);
        onClickJF.setOnClickListener(this);
        onClickSHARE.setOnClickListener(this);
        onClickTEACH.setOnClickListener(this);
        onClickMZX.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.onClick_SJ:
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_PROTOCOL_SJ);
                startActivity(intent);
                break;
            case R.id.onClick_DT:
                intent = new Intent(getActivity(), XTActivity.class);
                startActivity(intent);
                break;
            case R.id.onClick_EASY:
                intent = new Intent(getActivity(), EasyActivity.class);
                startActivity(intent);
                break;
            case R.id.onClick_XH:
                startXHIntent();
                break;
            case R.id.onClick_WH:
                intent = new Intent(getActivity(), WebViewNoTitleActivity.class);
                intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_URL_WH);
                startActivity(intent);
                break;
            case R.id.onClick_JF:
                if (UserStatus.isAuth(getActivity())) {
                    intent = new Intent(getActivity(), WebViewActivity.class);

                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AuthActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "请完善身份认证信息", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.onClick_SHARE:
                if (UserStatus.isAuth(getActivity())) {
                    intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_PROTOCOL_SHARE);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), AuthActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "请完善身份认证信息", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.onClick_TEACH:
                intent = new Intent(getActivity(), WebViewNoTitleActivity.class);
                intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_URL_JY);
                startActivity(intent);
                break;
            case R.id.onClick_MZX:
                intent = new Intent(getActivity(), WebViewNoTitleActivity.class);
                intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_URL_MZX);
                startActivity(intent);
                break;
        }
    }


    /**
     * 跳转应用至学生态校花
     */
    public void startXHIntent() {
        String uid = SpUtils.getUid(getActivity());
        try {
            PackageInfo packageinfo = getActivity().getPackageManager().getPackageInfo(ConstantsUtil.XH_PKG, 0);
            if (packageinfo == null) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstantsUtil.XH_360_URL));
                it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(it);
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String packageName = ConstantsUtil.XH_PKG;
            String className = ConstantsUtil.XH_CHECKOUT_CLASS;
            intent.setClassName(packageName, className);
            intent.putExtra("xh_uid", uid);
            startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求回调
     *
     * @param data
     */
    @Override
    public void onSuccess(PRedMessageBean data) {
        if (data.getLetter() + data.getMessage() + data.getScorerecord() > 0) {
            fxMessageShow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailure(int code, String msg) {

    }

    @Override
    public void onStartRequest() {

    }
}

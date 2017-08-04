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

import com.xiaohui.android.R;
import com.xiaohui.android.activity.xx.AddFriendActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XXFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.onClick_FS)
    RelativeLayout onClickFS;
    @BindView(R.id.onClick_GZ)
    RelativeLayout onClickGZ;
    @BindView(R.id.onClick_HY)
    ImageView onClickHY;
    Unbinder unbinder;

    public static XXFragment newInstance() {
        XXFragment fragment = new XXFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xx, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickFS.setOnClickListener(this);
        onClickGZ.setOnClickListener(this);
        onClickHY.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onClick_FS:
                break;
            case R.id.onClick_GZ:
                break;
            case R.id.onClick_HY:
                Intent intent = new Intent(getActivity(), AddFriendActivity.class);
                startActivity(intent);
                break;
        }
    }
}

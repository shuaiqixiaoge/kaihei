package com.xiaohui.android.fragment.xg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class XGBaseItemFragment extends Fragment {

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState);

    public abstract void setRefresh(String uid, String user_type, String price_type, String gap_type);

}

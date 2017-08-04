package com.xiaohui.android.adapter.recycle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhy.baselib.bean.response.PXGMainBean;
import com.lhy.baselib.network.HttpUrlManager;
import com.shell.view.recyclerview.adapter.BaseViewHolder;
import com.shell.view.recyclerview.adapter.ItemViewDelegate;
import com.xiaohui.android.R;
import com.xiaohui.android.util.ImageLoader;

/**
 * Created by zwy on 2017/5/5.
 * package_name is com.xiaohui.android.adapter.recycle
 * 描述:校股主页数据
 */

public class XGAdapter implements ItemViewDelegate<PXGMainBean> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_xgitem;
    }

    @Override
    public void convert(BaseViewHolder holder, PXGMainBean item, Object payload) {
        ImageLoader.getInstance(holder.itemView.getContext()).loadImg(item.getAvatar(), (ImageView) holder.getView(R.id.xg_img));
        holder.setText(R.id.xg_name, item.getReal_name());
    }
}

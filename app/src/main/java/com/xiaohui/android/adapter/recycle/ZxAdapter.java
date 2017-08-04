package com.xiaohui.android.adapter.recycle;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lhy.baselib.bean.response.PZxBean;
import com.shell.view.recyclerview.adapter.BaseViewHolder;
import com.shell.view.recyclerview.adapter.ItemViewDelegate;
import com.xiaohui.android.R;
import com.xiaohui.android.activity.web.WebViewWhiteTitleActivity;
import com.xiaohui.android.constants.WebConstants;
import com.xiaohui.android.util.ImageLoader;

/**
 * Created by zwy on 2017/4/27.
 * package_name is com.xiaohui.android.adapter
 * 描述:资讯
 */

public class ZxAdapter implements ItemViewDelegate<PZxBean.InformationListBean> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zx_item;
    }

    @Override
    public void convert(final BaseViewHolder holder, final PZxBean.InformationListBean item, Object payload) {
        holder.setText(R.id.zx_item_content, item.getTitle());
        ImageLoader.getInstance(holder.itemView.getContext()).loadImg(item.getTitle_img(), (ImageView) holder.getView(R.id.zx_item_img));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), WebViewWhiteTitleActivity.class);
                intent.putExtra(WebConstants.Key_Code, WebConstants.CODE_PROTOCOL_ZX);
                intent.putExtra(WebConstants.Key_Url, item.getLink());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
}

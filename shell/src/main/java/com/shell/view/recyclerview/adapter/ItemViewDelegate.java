package com.shell.view.recyclerview.adapter;

/**
 * Created by LiZhanPing on 2017/4/4.
 */

public interface ItemViewDelegate<T> {

    int getLayoutId();

    void convert(BaseViewHolder holder, T item, Object payload);
}

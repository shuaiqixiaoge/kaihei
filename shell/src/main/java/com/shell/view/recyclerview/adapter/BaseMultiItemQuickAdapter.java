package com.shell.view.recyclerview.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.shell.view.recyclerview.adapter.entity.MultiItemEntity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BaseMultiItemQuickAdapter<T> extends BaseQuickAdapter<T> {

    /**
     * layouts indexed with their types
     */
    private SparseArray<ItemViewDelegate<T>> layouts = new SparseArray<>();

    private ItemTypeLookup itemTypeLookup;

    private static final int DEFAULT_ITEM_TYPE = 0x0001;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseMultiItemQuickAdapter(List<T> data) {
        super(data);
    }

    public BaseMultiItemQuickAdapter() {
        super(null);
    }

    @Override
    protected final int getDefItemViewType(int position) {
        T item = getItem(position);
        if (item != null && item instanceof MultiItemEntity) {
            return ((MultiItemEntity) item).getItemType();
        }

        if (itemTypeLookup != null) {
            return itemTypeLookup.getItemType(position);
        }
        return DEFAULT_ITEM_TYPE;
    }

    @Override
    protected final BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    @Override
    protected void convert(BaseViewHolder holder, T item) {
    }

    @Override
    protected final void convert(BaseViewHolder holder, T item, Object payload) {
        ItemViewDelegate<T> itemDelegate = getItemDelegate(holder.getLayoutPosition() - getHeaderLayoutCount());
        if (itemDelegate != null)
            itemDelegate.convert(holder, item, payload);

        super.convert(holder, item, payload);
    }

    private ItemViewDelegate<T> getItemDelegate(int position) {
        return layouts.get(getDefItemViewType(position));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType).getLayoutId();
    }

    public final void setItemDefaultType(ItemViewDelegate<T> itemDelegate) {
        if (itemDelegate != null) {
            layouts.put(DEFAULT_ITEM_TYPE, itemDelegate);
            if (!isEmpty()) notifyDataSetChanged();
        }
    }

    public final void addItemType(int type, ItemViewDelegate<T> itemDelegate) {
        if (layouts.get(type) != null && itemDelegate != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + type
                            + ". Already registered ItemViewDelegate is "
                            + layouts.get(type));
        }
        layouts.put(type, itemDelegate);
        if (!isEmpty()) notifyDataSetChanged();
    }

    public final void removeItemType(ItemViewDelegate<T> itemDelegate) {
        if (itemDelegate != null) {
            int indexToRemove = layouts.indexOfValue(itemDelegate);
            if (indexToRemove >= 0) {
                layouts.removeAt(indexToRemove);
                if (!isEmpty()) notifyDataSetChanged();
            }
        }
    }

    public final void removeItemType(int type) {
        int indexToRemove = layouts.indexOfKey(type);

        if (indexToRemove >= 0) {
            layouts.removeAt(indexToRemove);
            if (!isEmpty()) notifyDataSetChanged();
        }
    }

    public void setItemTypeLookup(ItemTypeLookup itemTypeLookup) {
        this.itemTypeLookup = itemTypeLookup;
    }

    public interface ItemTypeLookup {
        int getItemType(int position);
    }
}



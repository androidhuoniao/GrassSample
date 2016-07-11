package com.grass.core.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by grass on 16/7/10.
 */

public abstract class CommonItemCreator<T extends CommonItemInfo, V extends RecyclerView.ViewHolder> {

    public abstract V createViewHolder(Context context, ViewGroup parent);

    public abstract void bindViewHolder(Context context, V holder, T commonItemInfo, int position);
}

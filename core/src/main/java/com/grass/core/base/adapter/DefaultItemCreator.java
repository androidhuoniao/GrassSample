package com.grass.core.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by grass on 16/7/10.
 */

public class DefaultItemCreator extends CommonItemCreator {
    @Override
    public RecyclerView.ViewHolder createViewHolder(Context context, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindViewHolder(Context context, RecyclerView.ViewHolder holder, CommonItemInfo commonItemInfo,
                               int position) {

    }
}

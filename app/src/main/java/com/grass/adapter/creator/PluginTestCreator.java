package com.grass.adapter.creator;

import com.example.anno.BindItem;
import com.grass.R;
import com.grass.adapter.item.ImageInfo;
import com.grass.core.base.adapter.CommonItemCreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by grass on 16/7/11.
 */
@BindItem(ImageInfo.class)
public class PluginTestCreator extends CommonItemCreator<ImageInfo, PluginTestCreator.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plugin, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void bindViewHolder(Context context, ViewHolder holder, ImageInfo info,
                               int position) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }
}

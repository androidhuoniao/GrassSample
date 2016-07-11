package com.grass.adapter.creator;

import com.bumptech.glide.Glide;
import com.grass.R;
import com.grass.adapter.item.ImageInfo;
import com.grass.core.base.adapter.CommonItemCreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by grass on 16/7/11.
 */

public class ImageInfoCreator extends CommonItemCreator<ImageInfo, ImageInfoCreator.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void bindViewHolder(Context context, ViewHolder holder, ImageInfo info,
                               int position) {
        Glide.with(context).load(info.getImagePath()).into(holder.mContentImg);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView mContentImg;
        public ImageInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentImg = (ImageView) view.findViewById(R.id.content_img);
        }
    }
}

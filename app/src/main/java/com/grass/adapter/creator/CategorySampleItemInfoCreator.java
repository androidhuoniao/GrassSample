package com.grass.adapter.creator;

import org.greenrobot.eventbus.EventBus;

import com.grass.R;
import com.grass.adapter.item.CategorySampleItemInfo;
import com.grass.core.base.adapter.CommonItemCreator;
import com.grass.core.event.EventOfChangeFragment;
import com.grass.core.event.EventOfOpenActivitySample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by grass on 16/7/10.
 */

public class CategorySampleItemInfoCreator extends CommonItemCreator<CategorySampleItemInfo,
        CategorySampleItemInfoCreator.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(Context context, final ViewHolder holder,
                               CategorySampleItemInfo commonItemInfo, int position) {
        holder.mItem = commonItemInfo;
        holder.name_Tv.setText(commonItemInfo.getName());
        holder.des_Tv.setText(commonItemInfo.getDescription());
        holder.mView.setBackgroundResource(R.drawable.common_card_bg);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.mItem.getType()) {
                    case FRAGMENT:
                        EventOfChangeFragment event = new EventOfChangeFragment(holder.mItem);
                        EventBus.getDefault().post(event);
                        break;

                    case ACTIVITY:
                        EventOfOpenActivitySample acevent = new EventOfOpenActivitySample(holder.mItem);
                        EventBus.getDefault().post(acevent);
                        break;
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name_Tv;
        public final TextView des_Tv;
        public CategorySampleItemInfo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name_Tv = (TextView) view.findViewById(R.id.name_tv);
            des_Tv = (TextView) view.findViewById(R.id.des_tv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name_Tv.getText() + "'";
        }
    }
}


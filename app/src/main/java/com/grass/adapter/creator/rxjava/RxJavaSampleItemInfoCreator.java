package com.grass.adapter.creator.rxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grass.R;
import com.grass.adapter.item.rxjava.RxJavaSampleInfo;
import com.grass.core.base.adapter.CommonItemCreator;

public abstract class RxJavaSampleItemInfoCreator<T extends RxJavaSampleInfo> extends CommonItemCreator<T, RxJavaSampleItemInfoCreator.ViewHolder> implements View.OnClickListener{

    public RxJavaSampleItemInfoCreator() {
    }

    @Override
    public ViewHolder createViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_rxjava_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(Context context, RxJavaSampleItemInfoCreator.ViewHolder holder, T commonItemInfo, int position) {
        holder.name_Tv.setText(commonItemInfo.mName);
        holder.mView.setBackgroundResource(R.drawable.common_card_bg);
        holder.mView.setOnClickListener(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name_Tv;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name_Tv = (TextView) view.findViewById(R.id.name_tv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name_Tv.getText() + "'";
        }
    }
}


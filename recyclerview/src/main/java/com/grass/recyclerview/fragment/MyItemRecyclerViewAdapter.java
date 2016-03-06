package com.grass.recyclerview.fragment;

import java.util.List;

import org.greenrobot.eventbus.EventBus;

import com.grass.core.bean.BaseSampleItemInfo;
import com.grass.core.event.EventOfChangeFragment;
import com.grass.core.event.EventOfOpenActivitySample;
import com.grass.recyclerview.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<BaseSampleItemInfo> mValues;

    public MyItemRecyclerViewAdapter(List<BaseSampleItemInfo> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BaseSampleItemInfo info = mValues.get(position);
        holder.mItem = info;
        holder.name_Tv.setText(info.getName());
        holder.des_Tv.setText(info.getDescription());
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

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name_Tv;
        public final TextView des_Tv;
        public BaseSampleItemInfo mItem;

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

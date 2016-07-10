package com.grass.core.base.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by grass on 16/7/10.
 */

public class CommonItemListAdapter extends RecyclerView.Adapter {

    private List<CommonItemInfo> mData = new ArrayList<>();
    private Context mContext;

    public CommonItemListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonItemCreator creator = CommonItemFactory.getCreator(viewType);
        if (creator == null) {
            throw new RuntimeException("creator is null");
        }
        return creator.createViewHolder(mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonItemInfo commonItemInfo = mData.get(position);
        CommonItemCreator creator = CommonItemFactory.getCreator(getItemViewType(position));
        if (creator == null) {
            throw new RuntimeException("creator is null");
        } else {
            creator.bindViewHolder(mContext, holder, commonItemInfo, position);
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return CommonItemFactory.getItemType(mData.get(position).getClass());
    }

    ////////////////////////////////////////////////////////////////////////////

    public void setData(List<CommonItemInfo> infos) {
        mData = infos;
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

}

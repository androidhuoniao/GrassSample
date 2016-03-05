package com.grass.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by grass on 11/1/15.
 */
public class ExtendViewholder extends RecyclerView.ViewHolder {

    private View mItemView;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public ExtendViewholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(mClickListener);
    }

    public void setClickListener(OnRecyclerViewItemClickListener clickListener) {
        mOnItemClickListener = clickListener;
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            if (null != mOnItemClickListener) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    };


}

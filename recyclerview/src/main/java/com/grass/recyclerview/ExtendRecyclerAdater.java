package com.grass.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by grass on 11/1/15.
 */
public class ExtendRecyclerAdater<VH extends ExtendRecyclerAdater.ExtendViewholder> extends RecyclerView.Adapter<ExtendRecyclerAdater.ExtendViewholder> implements OnRecyclerViewItemClickListener {


//    @Override
//    public void onBindViewHolder(VH holder, int position) {
//        holder.setClickListener(this);
//    }

    @Override
    public ExtendViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ExtendViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public void onItemClick(View child, int position) {

    }

    public static class ExtendViewholder extends RecyclerView.ViewHolder {

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
}



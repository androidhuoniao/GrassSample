package com.grass.adapter.creator;

import com.example.anno.BindItem;
import com.grass.R;
import com.grass.adapter.item.DouBanMovieItemInfo;
import com.grass.core.base.adapter.CommonItemCreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by grass on 16/7/10.
 */
@BindItem(DouBanMovieItemInfo.class)
public class DoubanMovieItemInfoCreator extends CommonItemCreator<DouBanMovieItemInfo,
        DoubanMovieItemInfoCreator.ViewHolder> {

    @Override
    public ViewHolder createViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(Context context, final ViewHolder holder,
                               DouBanMovieItemInfo commonItemInfo, int position) {
        holder.mItem = commonItemInfo;
        holder.name_Tv.setText(commonItemInfo.mMovie.getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name_Tv;
        public final TextView des_Tv;
        public DouBanMovieItemInfo mItem;

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


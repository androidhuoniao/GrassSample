package com.grass.demo.retrofit.DoubanMovie;

import java.util.List;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.grass.core.base.adapter.CommonItemInfo;
import com.grass.core.base.adapter.CommonItemListAdapter;
import com.grass.core.base.fragment.BaseVertialListFragment;
import com.grass.data.AppSamplesStore;
import com.grass.recyclerview.decoration.VerticalListDivider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

@BindTestCase(type = 1, name = "DoubanTop250Fragment", des = "获取豆瓣top250的电影")

public class DoubanTop250Fragment extends BaseVertialListFragment {

    private DouBanTop250Presenter mPresenter;

    public DoubanTop250Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DouBanTop250Presenter(this);
    }

    @Override
    public void initRecycleView(RecyclerView recyclerView) {
        super.initRecycleView(recyclerView);
        Drawable dividerDrawable = getResources().getDrawable(R.drawable.vertical_divider);
        recyclerView.addItemDecoration(new VerticalListDivider(dividerDrawable));
    }

    @Override
    public void initData(RecyclerView recyclerView) {
        CommonItemListAdapter listAdapter = new CommonItemListAdapter(getActivity());
        listAdapter.setData(AppSamplesStore.getFragmentSamples());
        recyclerView.setAdapter(listAdapter);
        mAdapter = listAdapter;
        mPresenter.loadData();
    }

    public void onDataReceived(List<? extends CommonItemInfo> list) {
        if (null != list) {
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();
        }
    }
}

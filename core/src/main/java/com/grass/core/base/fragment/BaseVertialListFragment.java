package com.grass.core.base.fragment;

import com.grass.core.R;
import com.grass.core.base.adapter.CommonItemListAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseVertialListFragment extends Fragment {
    public CommonItemListAdapter mAdapter;

    public BaseVertialListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        initRecycleView(recyclerView);
        initData(recyclerView);
        return view;
    }

    public void initRecycleView(RecyclerView recyclerView) {

    }

    public abstract void initData(RecyclerView recyclerView);
}

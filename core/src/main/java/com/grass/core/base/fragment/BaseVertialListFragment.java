package com.grass.core.base.fragment;

import java.util.List;

import com.grass.core.R;
import com.grass.core.base.adapter.CommonItemInfo;
import com.grass.core.base.adapter.CommonItemListAdapter;
import com.grass.core.base.mvp.IBaseMvpView;
import com.grass.core.base.mvp.MvpPresenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseVertialListFragment extends Fragment
        implements IBaseMvpView<List<CommonItemInfo>> {
    private CommonItemListAdapter mAdapter;

    protected RecyclerView mRecyclerView;

    protected MvpPresenter mMvpPresenter;

    public BaseVertialListFragment() {

    }

    protected CommonItemListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CommonItemListAdapter(getActivity());
        mMvpPresenter = createPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        mRecyclerView = recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);
        initRecycleView(recyclerView);
        return view;
    }

    public void initRecycleView(RecyclerView recyclerView) {

    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void dismissLoading() {

    }

    protected abstract MvpPresenter createPresenter();

    protected MvpPresenter getMvpPresenter() {
        return mMvpPresenter;
    }

    @Override
    public void onDataReceived(List<CommonItemInfo> data) {
        if (null == data) {
            throw new NullPointerException("data can not be null");
        }
        setData(data);
    }

    @Override
    public void setData(List<CommonItemInfo> data) {
        if (null != getAdapter()) {
            getAdapter().setData(data);
            notifyDataChanged();
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void notifyDataChanged() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mMvpPresenter) {
            mMvpPresenter.onDestory();
        }
    }
}

package com.grass.core.base.mvp;

import java.util.List;

import com.grass.core.R;
import com.grass.core.base.ErrorCode;
import com.grass.core.base.adapter.CommonItemInfo;
import com.grass.core.base.adapter.CommonItemListAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class MVPBaseVertialListFragment<P extends BaseListPresenter> extends Fragment implements IListView<P> {

    private RecyclerView mRecyclerView;
    private CommonItemListAdapter mCommonItemListAdapter;
    private P mPresenter;

    public MVPBaseVertialListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().loadData();
    }

    @Override
    public P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        return mPresenter;
    }

    @Override
    public void onError(ErrorCode code) {
        if (code == null) {
            return;
        }
        showCommonToast(code.toString());
    }

    @Override
    public void showCommonToast(String toast) {
        if (!toast.isEmpty()) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void notifyDatasetChanged() {
        if (mCommonItemListAdapter != null) {
            mCommonItemListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setData(List<? extends CommonItemInfo> infos) {
        if (mCommonItemListAdapter != null && null != infos) {
            mCommonItemListAdapter.setData(infos);
            mCommonItemListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDataReceived(List<? extends CommonItemInfo> infos) {
        setData(infos);
    }

    private void showEmptyView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestory();
        mPresenter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        initRecycleView(recyclerView);
        CommonItemListAdapter listAdapter = new CommonItemListAdapter(getActivity());
        mCommonItemListAdapter = listAdapter;
        listAdapter.setData(getPresenter().getData());
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    protected void initRecycleView(RecyclerView recyclerView) {

    }
}

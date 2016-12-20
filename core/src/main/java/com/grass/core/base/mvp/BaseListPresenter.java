package com.grass.core.base.mvp;

import java.util.ArrayList;
import java.util.List;

import com.grass.core.base.adapter.CommonItemInfo;

import android.content.Context;

/**
 * Created by grassswwang on 2016/12/19.
 */

public abstract class BaseListPresenter<V extends IListView> implements IPresenter<V> {

    private ArrayList<CommonItemInfo> mData = new ArrayList<>();
    protected V mIview;
    private Context mContext;

    public BaseListPresenter(Context context, V iview) {
        this.mContext = context;
        this.mIview = iview;
    }

    public List<CommonItemInfo> getData() {
        return mData;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public V getIView() {
        return mIview;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }
}

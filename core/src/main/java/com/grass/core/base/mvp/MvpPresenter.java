package com.grass.core.base.mvp;

/**
 * Created by grass on 16/7/31.
 */

public class MvpPresenter<V extends IBaseMvpView, T> implements IBaseMvpPresenter<V, T> {

    private V mView;

    @Override
    public void loadData() {

    }

    @Override
    public void reloadData() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public T getData() {
        return null;
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void deAttachView() {
        mView = null;
    }

    protected V getMvpView() {
        return mView;
    }

    @Override
    public boolean isViewAttached() {
        return mView == null;
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
    public void onDestory() {

    }
}

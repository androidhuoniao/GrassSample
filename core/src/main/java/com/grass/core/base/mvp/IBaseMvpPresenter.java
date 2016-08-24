package com.grass.core.base.mvp;

/**
 * Created by grass on 16/7/31.
 */

public interface IBaseMvpPresenter<V extends IBaseMvpView, T> {

    void loadData();

    void reloadData();

    void loadMoreData();

    T getData();

    void attachView(V view);

    void deAttachView();

    public boolean isViewAttached();

    //==========================lifecycle=======================

    void onStart();

    void onResume();

    void onPause();

    void onDestory();
}

package com.grass.core.base.mvp;

import android.support.annotation.UiThread;

/**
 * Created by grass on 16/7/31.
 */

public interface IBaseMvpView<T> {

    @UiThread
    public void showLoading(String message);

    @UiThread
    public void dismissLoading();

    @UiThread
    public void onDataReceived(T data);

    @UiThread
    public void setData(T data);

    @UiThread
    public void onError(String error);

    @UiThread
    public void showToast(String toast);

    @UiThread
    public void notifyDataChanged();

}

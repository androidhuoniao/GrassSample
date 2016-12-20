package com.grass.core.base.mvp;

import android.content.Context;

/**
 * Created by grassswwang on 2016/12/19.
 */

public interface IPresenter<V extends IView> {

    void loadData();

    void reloadData();

    V getIView();

    Context getContext();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestory();

}

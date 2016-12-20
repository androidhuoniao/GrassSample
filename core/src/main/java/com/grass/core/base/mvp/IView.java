package com.grass.core.base.mvp;

import com.grass.core.base.ErrorCode;

/**
 * Created by grassswwang on 2016/12/19.
 */

public interface IView<P extends IPresenter> {

    P createPresenter();

    P getPresenter();

    void notifyDatasetChanged();

    void onError(ErrorCode code);

    void showCommonToast(String toast);

    void showDialog();

    void dismissDialog();

}

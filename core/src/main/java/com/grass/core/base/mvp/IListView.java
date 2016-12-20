package com.grass.core.base.mvp;

import java.util.List;

import com.grass.core.base.adapter.CommonItemInfo;

/**
 * Created by grassswwang on 2016/12/19.
 */

public interface IListView<P extends IPresenter> extends IView<P> {
    void onDataReceived(List<? extends CommonItemInfo> infos);

    void setData(List<? extends CommonItemInfo> infos);
}

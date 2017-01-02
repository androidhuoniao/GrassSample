package com.grass.Prsenter;

import android.content.Context;

import com.grass.adapter.item.rxjava.RxTimerInfo;
import com.grass.core.base.adapter.CommonItemInfo;
import com.grass.core.base.mvp.BaseListPresenter;
import com.grass.fragment.RxJavaSampleListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grassswwang on 2016/12/19.
 */

public class RxJavaSamplelistPresenter extends BaseListPresenter<RxJavaSampleListFragment> {

    public RxJavaSamplelistPresenter(Context context, RxJavaSampleListFragment iview) {
        super(context, iview);
    }

    @Override
    public void loadData() {
        List<CommonItemInfo> data = createData();
        getData().addAll(data);
        getIView().setData(getData());
    }

    private List<CommonItemInfo> createData() {
        ArrayList<CommonItemInfo> infos = new ArrayList<>();
        infos.add(new RxTimerInfo());
        return infos;
    }

    @Override
    public void reloadData() {

    }

}

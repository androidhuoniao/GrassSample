package com.grass.event;

import com.grass.model.BaseSampleItemInfo;

import android.support.v4.app.Fragment;

/**
 * Created by grass on 15/11/19.
 */
public class EventOfChangeFragment {
    private BaseSampleItemInfo<Fragment> mInfo;

    public EventOfChangeFragment(BaseSampleItemInfo<Fragment> info) {
        mInfo = info;
    }

    public BaseSampleItemInfo<Fragment> getInfo() {
        return mInfo;
    }

    public void setInfo(BaseSampleItemInfo<Fragment> info) {
        mInfo = info;
    }
}

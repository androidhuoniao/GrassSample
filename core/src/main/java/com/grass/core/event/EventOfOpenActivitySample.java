package com.grass.core.event;

import com.grass.core.bean.BaseSampleItemInfo;

import android.app.Activity;

/**
 * Created by grass on 16/3/6.
 */
public class EventOfOpenActivitySample {

    private BaseSampleItemInfo<Activity> mInfo;

    public EventOfOpenActivitySample(BaseSampleItemInfo<Activity> info) {
        mInfo = info;
    }

    public BaseSampleItemInfo<Activity> getInfo() {
        return mInfo;
    }
}

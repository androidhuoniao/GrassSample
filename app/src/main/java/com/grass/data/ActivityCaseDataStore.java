package com.grass.data;

import java.util.ArrayList;

import com.grass.model.SampleActivityInfo;

/**
 * Created by grass on 15/12/20.
 */
public class ActivityCaseDataStore {

    private ArrayList<SampleActivityInfo> mList = new ArrayList<>();
    private static ActivityCaseDataStore ourInstance = new ActivityCaseDataStore();

    public static ActivityCaseDataStore getInstance() {
        return ourInstance;
    }

    private ActivityCaseDataStore() {
    }

    public void add(SampleActivityInfo info) {
        if (info == null) {
            return;
        }
        if (!mList.contains(info)) {
            mList.add(info);
        }
    }

    public void add(String name, String des, String fullClassName) {
        SampleActivityInfo info = new SampleActivityInfo(name, des, fullClassName);
        if (info == null) {
            return;
        }
        if (!mList.contains(info)) {
            mList.add(info);
        }
    }
}

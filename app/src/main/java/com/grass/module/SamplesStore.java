package com.grass.module;

import java.util.ArrayList;

import com.grass.fragment.RecyclerViewFragment;

/**
 * Created by grass on 15/11/18.
 */
public class SamplesStore {

    public static ArrayList<BaseSampleItemInfo> getFragmentSamples() {
        ArrayList<BaseSampleItemInfo> list = new ArrayList<>();
        BaseSampleItemInfo info = new BaseSampleItemInfo("RecyclerViewFragment", "介绍如何使用RecyclerViewFragment",
                RecyclerViewFragment.class);
        list.add(info);
        return list;
    }
}

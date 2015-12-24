package com.grass.module;

import java.util.ArrayList;

import com.grass.fragment.HorizontalRecyclerViewFragment;
import com.grass.fragment.PictureGridFragment;
import com.grass.fragment.RecyclerViewFragment;
import com.grass.model.BaseSampleItemInfo;

/**
 * Created by grass on 15/11/18.
 */
public class SamplesStore {

    public static ArrayList<BaseSampleItemInfo> getFragmentSamples() {
        ArrayList<BaseSampleItemInfo> list = new ArrayList<>();
        BaseSampleItemInfo info = new BaseSampleItemInfo("RecyclerViewFragment", "介绍如何使用RecyclerViewFragment",
                RecyclerViewFragment.class);
        list.add(info);

        BaseSampleItemInfo horiRecyInfo = new BaseSampleItemInfo("HorizontalRecyclerViewFragment",
                "介绍如何使用横向的RecyclerView",
                HorizontalRecyclerViewFragment.class);
        list.add(horiRecyInfo);


        BaseSampleItemInfo gridInfo = new BaseSampleItemInfo("PictureGridFragment",
                "使用grid的形式来展示缩略图",
                PictureGridFragment.class);
        list.add(gridInfo);

        return list;
    }
}

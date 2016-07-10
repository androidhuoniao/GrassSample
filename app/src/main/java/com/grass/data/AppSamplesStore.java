package com.grass.data;

import java.util.ArrayList;

import com.grass.core.base.SampeType;
import com.grass.core.bean.BaseSampleItemInfo;
import com.grass.fragment.HorizontalRecyclerViewFragment;
import com.grass.fragment.PictureGridFragment;

/**
 * Created by grass on 15/11/18.
 */
public class AppSamplesStore {

    public static ArrayList<BaseSampleItemInfo> getFragmentSamples() {
        ArrayList<BaseSampleItemInfo> list = new ArrayList<>();
        BaseSampleItemInfo horiRecyInfo = new BaseSampleItemInfo(SampeType.FRAGMENT,
                "HorizontalRecyclerViewFragment",
                "介绍如何使用横向的RecyclerView",
                HorizontalRecyclerViewFragment.class);
        list.add(horiRecyInfo);

        BaseSampleItemInfo gridInfo = new BaseSampleItemInfo(SampeType.FRAGMENT,
                "PictureGridFragment",
                "使用grid的形式来展示缩略图",
                PictureGridFragment.class);
        list.add(gridInfo);

        return list;
    }
}

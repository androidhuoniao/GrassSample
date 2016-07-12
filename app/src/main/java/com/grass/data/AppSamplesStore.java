package com.grass.data;

import java.util.ArrayList;

import com.grass.adapter.item.CategorySampleItemInfo;
import com.grass.core.base.SampeType;
import com.grass.fragment.AgeraImageListFragment;
import com.grass.fragment.HorizontalRecyclerViewFragment;
import com.grass.fragment.PictureGridFragment;

/**
 * Created by grass on 15/11/18.
 */
public class AppSamplesStore {

    public static ArrayList<CategorySampleItemInfo> getFragmentSamples() {
        ArrayList<CategorySampleItemInfo> list = new ArrayList<>();
        CategorySampleItemInfo horiRecyInfo = new CategorySampleItemInfo(SampeType.FRAGMENT,
                "HorizontalRecyclerViewFragment",
                "介绍如何使用横向的RecyclerView",
                HorizontalRecyclerViewFragment.class);
        list.add(horiRecyInfo);

        CategorySampleItemInfo gridInfo = new CategorySampleItemInfo(SampeType.FRAGMENT,
                "PictureGridFragment",
                "使用grid的形式来展示缩略图",
                PictureGridFragment.class);

        list.add(gridInfo);
        CategorySampleItemInfo ageraInfo = new CategorySampleItemInfo(SampeType.FRAGMENT,
                "AgeraImageListFragment",
                "使用grid的形式来展示缩略图",
                AgeraImageListFragment.class);
        list.add(ageraInfo);

        return list;
    }
}

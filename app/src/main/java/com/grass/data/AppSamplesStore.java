package com.grass.data;

import java.util.ArrayList;

import com.grass.adapter.item.CategorySampleItemInfo;

/**
 * Created by grass on 15/11/18.
 */
public class AppSamplesStore {

    public static ArrayList<CategorySampleItemInfo> LIST = new ArrayList<>();

    public static ArrayList<CategorySampleItemInfo> getFragmentSamples() {
        return LIST;
    }

    public static void registerCategoryTestCase(CategorySampleItemInfo info) {
        if (!LIST.contains(info)) {
            LIST.add(info);
        }
    }

}

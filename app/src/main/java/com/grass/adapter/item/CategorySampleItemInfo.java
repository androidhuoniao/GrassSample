package com.grass.adapter.item;

import com.grass.core.base.SampeType;
import com.grass.core.bean.BaseSampleItemInfo;

/**
 * Created by grass on 15/11/18.
 */
public class CategorySampleItemInfo extends BaseSampleItemInfo {

    static {
//        CommonItemFactory.registerCreator(CategorySampleItemInfo.class, new CategorySampleItemInfoCreator());
    }

    public CategorySampleItemInfo(SampeType type, String name, String des, Class sample) {
        super(type, name, des, sample);
    }
}

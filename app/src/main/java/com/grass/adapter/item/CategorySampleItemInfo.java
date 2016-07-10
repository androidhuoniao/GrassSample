package com.grass.adapter.item;

import com.grass.adapter.creator.CategorySampleItemInfoCreator;
import com.grass.core.base.SampeType;
import com.grass.core.base.adapter.CommonItemFactory;
import com.grass.core.base.adapter.CommonItemInfo;
import com.grass.core.base.adapter.CommonItemInfoTypes;

/**
 * Created by grass on 15/11/18.
 */
public class CategorySampleItemInfo extends CommonItemInfo {
    private String mDescription;
    private Class mSample;
    private String mName;
    private SampeType mType;

    static {
        CommonItemFactory.registerCreator(CategorySampleItemInfo.class, new CategorySampleItemInfoCreator());
    }

    public CategorySampleItemInfo(SampeType type, String name, String des, Class sample) {
        mType = type;
        mName = name;
        mDescription = des;
        mSample = sample;
    }

    @Override
    public int getCommonType() {
        return CommonItemInfoTypes.TYPE_CATEGORY_SAMPLE;
    }

    public SampeType getType() {
        return mType;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Class getSample() {
        return mSample;
    }

    public void setSample(Class sample) {
        mSample = sample;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}

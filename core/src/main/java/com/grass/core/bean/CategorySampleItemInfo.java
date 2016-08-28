package com.grass.core.bean;

/**
 * Created by grass on 15/11/18.
 */
public class CategorySampleItemInfo extends BaseSampleItemInfo {

    public CategorySampleItemInfo(SampleType type, String name, String des, Class sample) {
        super(type, name, des, sample);
    }

    public static CategorySampleItemInfo createInstance(int type, String name, String des, Class sample) {

        return new CategorySampleItemInfo(SampleType.values()[type], name, des, sample);
    }
    public static CategorySampleItemInfo createInstance(SampleType type, String name, String des, Class sample) {

        return new CategorySampleItemInfo(type, name, des, sample);
    }
}

package com.grass.module;

/**
 * Created by grass on 15/11/18.
 */
public class BaseSampleItemInfo<T> {
    private String mDescription;
    private Class<T> mSample;
    private String mName;

    public BaseSampleItemInfo(String name, String des, Class<T> sample) {
        mName = name;
        mDescription = des;
        mSample = sample;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Class<T> getSample() {
        return mSample;
    }

    public void setSample(Class<T> sample) {
        mSample = sample;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}

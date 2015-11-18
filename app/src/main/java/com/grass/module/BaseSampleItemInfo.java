package com.grass.module;

/**
 * Created by grass on 15/11/18.
 */
public class BaseSampleItemInfo<T> {
    private String mDescription;
    private T mSample;
    private String mName;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public T getSample() {
        return mSample;
    }

    public void setSample(T sample) {
        mSample = sample;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}

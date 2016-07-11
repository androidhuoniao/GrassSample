package com.grass.core.bean;

import com.grass.core.base.SampeType;
import com.grass.core.base.adapter.CommonItemInfo;

/**
 * Created by grass on 15/11/18.
 */
public class BaseSampleItemInfo<T> extends CommonItemInfo{
    private String mDescription;
    private Class<T> mSample;
    private String mName;
    private SampeType mType;

    public BaseSampleItemInfo(SampeType type, String name, String des, Class<T> sample) {
        mType = type;
        mName = name;
        mDescription = des;
        mSample = sample;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseSampleItemInfo<?> that = (BaseSampleItemInfo<?>) o;

        if (mSample != null ? !mSample.equals(that.mSample) : that.mSample != null) {
            return false;
        }
        return mName != null ? mName.equals(that.mName) : that.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mSample != null ? mSample.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }
}

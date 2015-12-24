package com.grass.model;

/**
 * Created by grass on 15/12/24.
 */
public class SampleActivityInfo {
    private String mName;
    private String mDes;
    private String mFullClassName;

    public SampleActivityInfo(String name, String des, String fullClassName) {
        mName = name;
        mDes = des;
        mFullClassName = fullClassName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        mDes = des;
    }

    public String getFullClassName() {
        return mFullClassName;
    }

    public void setFullClassName(String fullClassName) {
        mFullClassName = fullClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SampleActivityInfo that = (SampleActivityInfo) o;

        if (mName != null ? !mName.equals(that.mName) : that.mName != null) {
            return false;
        }
        if (mDes != null ? !mDes.equals(that.mDes) : that.mDes != null) {
            return false;
        }
        return !(mFullClassName != null ? !mFullClassName.equals(that.mFullClassName) : that.mFullClassName != null);

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mDes != null ? mDes.hashCode() : 0);
        result = 31 * result + (mFullClassName != null ? mFullClassName.hashCode() : 0);
        return result;
    }
}

package com.grass.mediastore;

/**
 * Created by grass on 15/11/22.
 */
public class ImageItemInfo {

    private long mImageId;
    private String mPath;

    public ImageItemInfo(long imageId, String path) {
        mImageId = imageId;
        mPath = path;
    }

    public long getImageId() {
        return mImageId;
    }

    public void setImageId(long imageId) {
        mImageId = imageId;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }
}

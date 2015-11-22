package com.grass.mediastore;

/**
 * Created by grass on 15/11/22.
 */
public class ThumbnailItemInfo {

    private int mThumbId;
    private int mOriginalId;
    private String mThumbPath;

    public ThumbnailItemInfo(int thumbId, int originalId, String thumbPath) {
        mThumbId = thumbId;
        mOriginalId = originalId;
        mThumbPath = thumbPath;
    }

    public int getThumbId() {
        return mThumbId;
    }

    public void setThumbId(int thumbId) {
        mThumbId = thumbId;
    }

    public int getOriginalId() {
        return mOriginalId;
    }

    public void setOriginalId(int originalId) {
        mOriginalId = originalId;
    }

    public String getThumbPath() {
        return mThumbPath;
    }

    public void setThumbPath(String thumbPath) {
        mThumbPath = thumbPath;
    }
}

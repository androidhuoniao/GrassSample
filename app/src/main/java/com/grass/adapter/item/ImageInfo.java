package com.grass.adapter.item;

import com.grass.adapter.creator.ImageInfoCreator;
import com.grass.core.base.adapter.CommonItemFactory;
import com.grass.core.base.adapter.CommonItemInfo;

/**
 * Created by grass on 16/7/11.
 */

public class ImageInfo extends CommonItemInfo {
    private String mImagePath;
    private long mImageID;


    static {
        CommonItemFactory.registerCreator(ImageInfo.class, new ImageInfoCreator());
    }
    public ImageInfo() {
    }

    public ImageInfo(String imagePath, long imageID) {
        mImagePath = imagePath;
        mImageID = imageID;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public long getImageID() {
        return mImageID;
    }

    public void setImageID(long imageID) {
        mImageID = imageID;
    }
}

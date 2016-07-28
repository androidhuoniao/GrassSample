package com.grass.data;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by grass on 16/5/13.
 */
public class CommonContentItem implements Serializable, Parcelable {

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    public int ctype;//内容类型，1-文字，2-图片
    public String value;//内容
    public int height;//图片高度
    public int width;//图片宽度

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ctype);
        dest.writeString(this.value);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
    }

    public CommonContentItem() {
    }

    protected CommonContentItem(Parcel in) {
        this.ctype = in.readInt();
        this.value = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
    }

    public static final Creator<CommonContentItem> CREATOR = new Creator<CommonContentItem>() {
        @Override
        public CommonContentItem createFromParcel(Parcel source) {
            return new CommonContentItem(source);
        }

        @Override
        public CommonContentItem[] newArray(int size) {
            return new CommonContentItem[size];
        }
    };
}

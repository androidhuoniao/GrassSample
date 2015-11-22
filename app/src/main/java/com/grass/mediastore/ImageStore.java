package com.grass.mediastore;

import java.util.ArrayList;

import com.socks.library.KLog;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

/**
 * Created by grass on 15/11/22.
 */
public class ImageStore {

    private static final String[] THUMBNAIL_PROJECTION =
            new String[] {MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.Thumbnails.IMAGE_ID,
                    MediaStore.Images.Thumbnails.DATA};

    public static ArrayList<ThumbnailItemInfo> queryThumbnails(Context context) {
        Cursor cursor = MediaStore.Images.Thumbnails.queryMiniThumbnails(context.getContentResolver(),
                MediaStore.Images.Thumbnails
                        .INTERNAL_CONTENT_URI, MediaStore.Images.Thumbnails.MINI_KIND, THUMBNAIL_PROJECTION);
        KLog.i("thumb", "count: " + cursor.getCount());
        ArrayList<ThumbnailItemInfo> list = new ArrayList<>(cursor.getCount());
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                .moveToNext()) {
            int thumbId = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Thumbnails._ID));
            int imageId = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
            KLog.i("thumb", "path: " + path);
            ThumbnailItemInfo info = new ThumbnailItemInfo(thumbId, imageId, path);
            list.add(info);
        }
        cursor.close();
        return list;
    }

    public static ArrayList<ImageItemInfo> queryImages(Context context) {
        String[] proj = {
                MediaStore.Images.Media._ID, MediaStore.Images.Media.TITLE,
                Images.Media.DISPLAY_NAME, Images.Media.DESCRIPTION,
                Images.Media.DATA, Images.Media.MINI_THUMB_MAGIC,
                Images.Media.MIME_TYPE
        };
        Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(),
                Images.Media.INTERNAL_CONTENT_URI, proj);
        ArrayList<ImageItemInfo> list = new ArrayList<>(cursor.getCount());
        KLog.i("thumb", "image count: " + cursor.getCount());
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                .moveToNext()) {
            long origid = cursor.getLong(cursor
                    .getColumnIndex(Images.Media._ID));
            String title = cursor.getString(cursor
                    .getColumnIndex(Images.Media.TITLE));
            String displayName = cursor.getString(cursor
                    .getColumnIndex(Images.Media.DISPLAY_NAME));
            String des = cursor.getString(cursor
                    .getColumnIndex(Images.Media.DESCRIPTION));
            String path = cursor.getString(cursor
                    .getColumnIndex(Images.Media.DATA));
            String thumbId = cursor.getString(cursor
                    .getColumnIndex(Images.Media.MINI_THUMB_MAGIC));
            String miniType = cursor.getString(cursor
                    .getColumnIndex(Images.Media.MIME_TYPE));
            ImageItemInfo info = new ImageItemInfo(origid, title);
            list.add(info);
        }
        cursor.close();
        return list;
    }

}

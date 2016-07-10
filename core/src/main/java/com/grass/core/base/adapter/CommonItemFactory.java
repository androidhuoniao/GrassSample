package com.grass.core.base.adapter;

import static android.provider.ContactsContract.CommonDataKinds.Nickname.TYPE_DEFAULT;

import java.util.concurrent.atomic.AtomicInteger;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

/**
 * Created by grass on 16/7/10.
 */

public class CommonItemFactory {

    private static SparseArray<CommonItemCreator> mCreatorsMap = new SparseArray<>(10);
    private static ArrayMap<Class, Integer> mIndexMap = new ArrayMap<>();
    private static AtomicInteger mIndexGenerator = new AtomicInteger();

    public static <T extends CommonItemInfo> void registerCreator(Class<T> infoClass, CommonItemCreator
            creator) {
        if (!mIndexMap.containsKey(infoClass)) {
            int type = mIndexGenerator.incrementAndGet();
            mIndexMap.put(infoClass, type);
            mCreatorsMap.put(type, creator);
        }
    }

    public static CommonItemCreator getCreator(int itemType) {
        if (itemType == CommonItemInfoTypes.TYPE_DEFAULT) {
            return new DefaultItemCreator();
        }
        CommonItemCreator commonItemCreator = mCreatorsMap.get(itemType);
        return commonItemCreator;
    }

    public static <T extends CommonItemInfo> int getItemType(Class<T> infoClass) {
        if (!mIndexMap.containsKey(infoClass)) {
            return CommonItemInfoTypes.TYPE_DEFAULT;
        }
        return mIndexMap.get(infoClass);
    }

}

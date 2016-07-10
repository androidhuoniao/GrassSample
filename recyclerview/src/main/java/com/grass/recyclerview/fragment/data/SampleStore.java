package com.grass.recyclerview.fragment.data;

import java.util.ArrayList;
import java.util.List;

import com.grass.core.base.SampeType;
import com.grass.core.bean.BaseSampleItemInfo;
import com.grass.recyclerview.dummy.BookFragment;

public class SampleStore {

    public static final List<BaseSampleItemInfo> ITEMS = new ArrayList<BaseSampleItemInfo>();

    static {
        BaseSampleItemInfo<BookFragment> bookInfo = new BaseSampleItemInfo<>(SampeType.FRAGMENT,
                "测试例子",
                "",
                BookFragment.class);
        addItem(bookInfo);


    }

    private static void addItem(BaseSampleItemInfo item) {
        ITEMS.add(item);
    }
}

package com.grass.adapter.creator.rxjava;

import android.view.View;

import com.example.anno.BindItem;
import com.grass.adapter.item.rxjava.RxFlatMapInfo;

/**
 * Created by grass on 2017/1/2.
 */

@BindItem(RxFlatMapInfo.class)
public class RxFlatmapCreator extends RxJavaSampleItemInfoCreator<RxFlatMapInfo> {
    @Override
    public void onClick(View v) {
        getUserAccountInfo();
    }

    /**
     * 模拟登录，获取token，然后获取详细信息的流程
     */

    private void getUserAccountInfo() {

    }

    static class Token {
        public String token;
    }

    static class AccountInfo {
        public long duration;
    }
}

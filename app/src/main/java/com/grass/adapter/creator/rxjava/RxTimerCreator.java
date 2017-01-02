package com.grass.adapter.creator.rxjava;

import android.view.View;

import com.example.anno.BindItem;
import com.grass.adapter.item.rxjava.RxTimerInfo;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by grass on 2017/1/2.
 */

@BindItem(RxTimerInfo.class)
public class RxTimerCreator extends RxJavaSampleItemInfoCreator<RxTimerInfo> {
    @Override
    public void onClick(View v) {
        Observable.timer(3, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                return "grass"+aLong;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.i("RxTimer: onNext: "+s);
            }
        });
    }
}

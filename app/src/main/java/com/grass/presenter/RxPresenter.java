package com.grass.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by grass on 16/8/16.
 */

public class RxPresenter {

    public RxPresenter() {
    }

    public void merageOption() {
        Observable.merge(getAskObservable(), getAnswerObservable())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("onNext: " + s);
                    }
                });
    }

    private Observable<String> getAskObservable() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                ArrayList<String> strings = new ArrayList<>(10);
                for (int i = 0; i < 10; i++) {
                    strings.add("ask--" + i);
                }
                subscriber.onNext(strings);
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).delay(0, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io());

    }

    private Observable<String> getAnswerObservable() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                ArrayList<String> strings = new ArrayList<>(10);
                for (int i = 0; i < 10; i++) {
                    strings.add("answer--" + i);
                }
                subscriber.onNext(strings);
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).delay(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io());

    }
}

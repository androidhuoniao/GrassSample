package com.grass.helper;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by grass on 2017/1/2.
 */

public class ObservableHelper {

    public static Observable<String> getNetObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(2000);
                    subscriber.onNext("net is complete");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<String> getMemroyErrorObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onError(new Throwable("memory error"));
                subscriber.onCompleted();
            }
        });
    }

    public static Observable<String> getMemroyObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("memroy is complete");
                subscriber.onCompleted();
            }
        });
    }

    public static Observable<String> getMemroySleepObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("memroy is complete");
                subscriber.onCompleted();
            }
        });
    }

    public static Observable<String> getDiskObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                    subscriber.onNext("Disk is complete");
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}

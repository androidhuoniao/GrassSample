package com.grass.presenter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grass on 16/8/21.
 */

public class RxHelper {

    public static <T> Observable.Transformer<T, T> io() {
        return new IOTransformer<>();
    }

    public static class MyTransformer<T> implements Observable.Transformer<T, T> {

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return null;
        }
    }

    public static class IOTransformer<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> tObservable) {
            tObservable.subscribeOn(Schedulers.io());
            tObservable.observeOn(AndroidSchedulers.mainThread());
            return tObservable;
        }
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    final Observable.Transformer schedulersTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    <T> Observable.Transformer<T, T> applyIOSchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}

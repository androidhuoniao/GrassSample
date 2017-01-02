package com.grass.adapter.creator.rxjava;

import android.text.TextUtils;
import android.view.View;

import com.example.anno.BindItem;
import com.grass.adapter.item.rxjava.RxConcatInfo;
import com.grass.helper.ObservableHelper;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by grass on 2017/1/2.
 */

@BindItem(RxConcatInfo.class)
public class RxConcatCreator extends RxJavaSampleItemInfoCreator<RxConcatInfo> {
    @Override
    public void onClick(View v) {
        testConcat1();
        testConcat2();
        testConcat3();
        testConcat4();
    }

    private void testConcat1() {
        Logger.i("--------------------------------------------------------------------------------1----");
        Observable.concat(ObservableHelper.getMemroyObservable(), ObservableHelper.getDiskObservable(), ObservableHelper.getNetObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("RxConcat1: onComplete-------");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.i("RxConcat1: onError: " + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("RxConcat1: onNext: " + s);

                    }
                });
    }

    private void testConcat2() {
        Logger.i("--------------------------------------------------------------------------------2----");
        Observable.concat(ObservableHelper.getMemroyObservable(), ObservableHelper.getDiskObservable(), ObservableHelper.getNetObservable())
                .takeFirst(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("RxConcat2: onComplete ");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.i("RxConcat2: onError: " + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("RxConcat2: onNext: " + s);

                    }
                });
    }

    private void testConcat3() {
        Logger.i("--------------------------------------------------------------------------------3----");
        Observable.concat(ObservableHelper.getMemroyErrorObservable(), ObservableHelper.getDiskObservable(), ObservableHelper.getNetObservable())
                .takeFirst(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("RxConcat3: onComplete ");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.i("RxConcat3: onError: " + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("RxConcat3: onNext: " + s);

                    }
                });
    }

    private void testConcat4() {
        Logger.i("--------------------------------------------------------------------------------4----");
        Observable.concat(ObservableHelper.getMemroySleepObservable(), ObservableHelper.getDiskObservable(), ObservableHelper.getNetObservable())
                .takeFirst(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("RxConcat4: onComplete ");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.i("RxConcat4: onError: " + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.i("RxConcat4: onNext: " + s);

                    }
                });
    }
}

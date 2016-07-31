package com.grass.demo.retrofit.DoubanMovie;

import java.util.ArrayList;
import java.util.List;

import com.grass.adapter.item.DouBanMovieItemInfo;
import com.grass.core.base.mvp.IBaseMvpView;
import com.grass.core.base.mvp.MvpPresenter;

import android.util.Log;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by grass on 16/7/31.
 */

public class DouBanTop250Presenter extends MvpPresenter<IBaseMvpView, DoubanTop250MovieResponse> {

    private DoubanTop250MovieRetrofit mDoubanRetrofit;

    public DouBanTop250Presenter() {
        mDoubanRetrofit = new DoubanTop250MovieRetrofit();
    }

    @Override
    public void loadData() {
        getMovies(0, 25);
    }

    private void getMovies(int start, int count) {
        mDoubanRetrofit.getRetrofit()
                .create(DouBanTop250Service.class)
                .getDoubanMovies(start, count)
                .map(new Func1<DoubanTop250MovieResponse, List<DouBanMovieItemInfo>>() {
                    @Override
                    public List<DouBanMovieItemInfo> call(DoubanTop250MovieResponse doubanTop250MovieResponse) {
                        ArrayList<DouBanMovieItemInfo> itemInfos = new ArrayList<>();
                        for (DoubanMovie subject : doubanTop250MovieResponse.subjects) {
                            DouBanMovieItemInfo info = new DouBanMovieItemInfo();
                            info.mMovie = subject;
                            itemInfos.add(info);
                        }
                        return itemInfos;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getMvpView().showLoading("");
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getMvpView().dismissLoading();
                    }
                })
                .subscribe(new Subscriber<List<DouBanMovieItemInfo>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("grass", "onCompleted is working");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("grass", "onError is working" + e.getMessage());
                    }

                    @Override
                    public void onNext(List<DouBanMovieItemInfo> doubanMovies) {
                        Log.i("grass", "onNext is working " + doubanMovies.size());
                        for (DouBanMovieItemInfo doubanMovy : doubanMovies) {
                            Log.i("grass", "movie: " + doubanMovy.mMovie.getTitle());
                        }
                        getMvpView().onDataReceived(doubanMovies);
                    }
                });

    }
}

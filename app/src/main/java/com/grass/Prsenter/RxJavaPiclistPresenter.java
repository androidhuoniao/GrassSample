package com.grass.Prsenter;

import java.util.ArrayList;
import java.util.List;

import com.grass.adapter.item.ImageInfo;
import com.grass.core.base.mvp.BaseListPresenter;
import com.grass.fragment.RxJavaPicListFragment;
import com.grass.mediastore.ImageItemInfo;
import com.grass.mediastore.ImageStore;

import android.content.Context;
import android.widget.Toast;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by grassswwang on 2016/12/19.
 */

public class RxJavaPiclistPresenter extends BaseListPresenter<RxJavaPicListFragment> {

    public RxJavaPiclistPresenter(Context context, RxJavaPicListFragment iview) {
        super(context, iview);
    }

    @Override
    public void loadData() {
        Observable.create(new Observable.OnSubscribe<List<? extends ImageItemInfo>>() {
            @Override
            public void call(Subscriber<? super List<? extends ImageItemInfo>> subscriber) {
                ArrayList<ImageItemInfo>
                        imageItemInfos = ImageStore.queryImages(getContext());
                subscriber.onNext(imageItemInfos);
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<List<? extends ImageItemInfo>, Observable<ImageItemInfo>>() {
            @Override
            public Observable<ImageItemInfo> call(List<? extends ImageItemInfo> imageItemInfos) {
                return Observable.from(imageItemInfos);
            }
        }).map(new Func1<ImageItemInfo, ImageInfo>() {
            @Override
            public ImageInfo call(ImageItemInfo imageItemInfo) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setImageID(imageItemInfo.getImageId());
                imageInfo.setImagePath(imageItemInfo.getPath());
                return imageInfo;
            }
        }).toList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ImageInfo>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getContext(), "load complete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<ImageInfo> imageInfos) {
                        getIView().setData(imageInfos);
                    }
                });
    }

    @Override
    public void reloadData() {

    }

}

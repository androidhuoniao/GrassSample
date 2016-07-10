package com.grass.fragment;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.grass.R;
import com.grass.mediastore.ImageItemInfo;
import com.grass.mediastore.ImageStore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PictureGridFragment extends Fragment {

    RecyclerView mRecyclerView;

    private PictureAdapter mPictureAdapter;

    public PictureGridFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_grid, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        PictureAdapter adapter = new PictureAdapter();
        mPictureAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
        loadPictures();
        return view;
    }

    private void loadPictures() {
        Observable<ArrayList<ImageItemInfo>> observable = Observable.create(
                new Observable.OnSubscribe<ArrayList<ImageItemInfo>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<ImageItemInfo>> subscriber) {
                        ArrayList<ImageItemInfo> list = ImageStore.queryImages(getActivity());
                        if (list != null && !list.isEmpty()) {
                            subscriber.onNext(list);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new IllegalAccessException("没有数据"));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ArrayList<ImageItemInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ArrayList<ImageItemInfo> imageItemInfos) {
                if (null != imageItemInfos && !imageItemInfos.isEmpty()) {
                    mPictureAdapter.setData(imageItemInfos);
                }
            }
        });
    }

    class PictureAdapter extends RecyclerView.Adapter<PictureHolder> {

        private ArrayList<ImageItemInfo> mList;

        @Override
        public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            PictureHolder holder = new PictureHolder(View.inflate(getActivity(), R.layout.item_picture, null));
            return holder;
        }

        @Override
        public void onBindViewHolder(PictureHolder holder, int position) {
            ImageItemInfo info = mList.get(position);
            Glide.with(PictureGridFragment.this).load(info.getPath()).into(holder.mPicImg);
        }

        @Override
        public int getItemCount() {
            return null == mList ? 0 : mList.size();
        }

        public void setData(ArrayList<ImageItemInfo> list) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    class PictureHolder extends RecyclerView.ViewHolder {

        ImageView mPicImg;

        public PictureHolder(View itemView) {
            super(itemView);
            mPicImg = (ImageView) itemView.findViewById(R.id.pictureImg);
        }
    }
}

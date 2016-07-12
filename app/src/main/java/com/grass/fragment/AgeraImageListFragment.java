package com.grass.fragment;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import com.google.android.agera.Function;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;
import com.grass.R;
import com.grass.adapter.item.ImageInfo;
import com.grass.core.base.adapter.CommonItemListAdapter;
import com.grass.core.base.fragment.BaseVertialListFragment;
import com.grass.data.AppSamplesStore;
import com.grass.mediastore.ImageItemInfo;
import com.grass.mediastore.ImageStore;
import com.grass.recyclerview.decoration.VerticalListDivider;
import com.orhanobut.logger.Logger;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class AgeraImageListFragment extends BaseVertialListFragment {

    private RecyclerView mRecycleView;
    private CommonItemListAdapter mAdapter;

    public AgeraImageListFragment() {

    }

    @SuppressWarnings("unused")
    public static AgeraImageListFragment newInstance() {
        AgeraImageListFragment fragment = new AgeraImageListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initRecycleView(RecyclerView recyclerView) {
        super.initRecycleView(recyclerView);
        Drawable dividerDrawable = getResources().getDrawable(R.drawable.vertical_divider);
        recyclerView.addItemDecoration(new VerticalListDivider(dividerDrawable));
        mRecycleView = recyclerView;
    }

    @Override
    public void initData(RecyclerView recyclerView) {
        CommonItemListAdapter listAdapter = new CommonItemListAdapter(getActivity());
        listAdapter.setData(AppSamplesStore.getFragmentSamples());
        recyclerView.setAdapter(listAdapter);
        mAdapter = listAdapter;
        loadImages();
    }

    private void loadImages() {
        Repository<ArrayList<ImageInfo>> imageRepo = Repositories.repositoryWithInitialValue(new ArrayList<ImageInfo>())
                .observe()
                .onUpdatesPerLoop()
                .goTo(Executors.newSingleThreadExecutor())
                .getFrom(imagesSupplier)
                .thenTransform(convertFun)
                .compile();
        imageRepo.addUpdatable(imagesUpdateable);
        mImageRepo = imageRepo;
    }

    private Repository<ArrayList<ImageInfo>> mImageRepo;

    private Updatable imagesUpdateable = new Updatable() {
        @Override
        public void update() {
            if (mImageRepo != null) {
                ArrayList<ImageInfo> imageInfos = mImageRepo.get();
                mAdapter.setData(imageInfos);
            }
        }
    };
    private Supplier<ArrayList<ImageItemInfo>> imagesSupplier = new Supplier<ArrayList<ImageItemInfo>>() {
        @NonNull
        @Override
        public ArrayList<ImageItemInfo> get() {
            ArrayList<ImageItemInfo>
                    imageItemInfos = ImageStore.queryImages(AgeraImageListFragment.this.getActivity());
            Logger.i("" + imageItemInfos.size());
            return imageItemInfos;
        }
    };

    private Function<ArrayList<ImageItemInfo>, ArrayList<ImageInfo>> convertFun =
            new Function<ArrayList<ImageItemInfo>, ArrayList<ImageInfo>>() {

                @NonNull
                @Override
                public ArrayList<ImageInfo> apply(@NonNull ArrayList<ImageItemInfo> input) {
                    ArrayList<ImageInfo> imageInfos = new ArrayList<>(input.size());
                    for (ImageItemInfo imageItemInfo : input) {
                        ImageInfo imageInfo = new ImageInfo();
                        imageInfo.setImageID(imageItemInfo.getImageId());
                        imageInfo.setImagePath(imageItemInfo.getPath());
                        imageInfos.add(imageInfo);
                    }
                    return imageInfos;
                }
            };

}

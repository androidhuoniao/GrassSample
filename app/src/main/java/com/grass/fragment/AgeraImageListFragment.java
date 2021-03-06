package com.grass.fragment;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import com.example.anno.BindTestCase;
import com.google.android.agera.Function;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;
import com.grass.R;
import com.grass.adapter.item.ImageInfo;
import com.grass.core.base.fragment.BaseVertialListFragment;
import com.grass.core.base.mvp.MvpPresenter;
import com.grass.core.data.AppSamplesStore;
import com.grass.mediastore.ImageItemInfo;
import com.grass.mediastore.ImageStore;
import com.grass.recyclerview.decoration.VerticalListDivider;
import com.orhanobut.logger.Logger;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

@BindTestCase(type = 1, name = "AgeraImageListFragment", des = "介绍如何使用google agera框架")
public class AgeraImageListFragment extends BaseVertialListFragment {

    private RecyclerView mRecycleView;

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
        loadImages();
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
        getAdapter().setData(AppSamplesStore.getFragmentSamples());
    }

    @Override
    protected MvpPresenter createPresenter() {
        return null;
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
                getAdapter().setData(imageInfos);
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

package com.grass.fragment;

import com.example.anno.BindTestCase;
import com.grass.Prsenter.RxJavaPiclistPresenter;
import com.grass.core.base.mvp.MVPBaseVertialListFragment;

import android.os.Bundle;

@BindTestCase(type = 1, name = "RxJavaPicListFragment", des = "介绍如何使用RxJava加载图片")
public class RxJavaPicListFragment extends MVPBaseVertialListFragment<RxJavaPiclistPresenter> {

    public RxJavaPicListFragment() {
    }

    @SuppressWarnings("unused")
    public static RxJavaPicListFragment newInstance() {
        RxJavaPicListFragment fragment = new RxJavaPicListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public RxJavaPiclistPresenter createPresenter() {
        return new RxJavaPiclistPresenter(getActivity(), this);
    }

}

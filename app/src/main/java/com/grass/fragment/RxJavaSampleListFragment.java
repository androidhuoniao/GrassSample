package com.grass.fragment;

import android.os.Bundle;

import com.example.anno.BindTestCase;
import com.grass.Prsenter.RxJavaSamplelistPresenter;
import com.grass.core.base.mvp.MVPBaseVertialListFragment;

@BindTestCase(type = 1, name = "RxJavaSampleListFragment", des = "介绍如何使用RxJava的例子集锦")
public class RxJavaSampleListFragment extends MVPBaseVertialListFragment<RxJavaSamplelistPresenter> {

    public RxJavaSampleListFragment() {
    }

    @SuppressWarnings("unused")
    public static RxJavaSampleListFragment newInstance() {
        RxJavaSampleListFragment fragment = new RxJavaSampleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public RxJavaSamplelistPresenter createPresenter() {
        return new RxJavaSamplelistPresenter(getActivity(), this);
    }
}

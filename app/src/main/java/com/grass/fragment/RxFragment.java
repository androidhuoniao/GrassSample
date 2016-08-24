package com.grass.fragment;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.grass.presenter.RxPresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by grass on 16/8/16.
 */
@BindTestCase(type = 1, name = "RxFragment", des = "介绍如何使用rxJava")
public class RxFragment extends Fragment {

    private RxPresenter mRxPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxPresenter = new RxPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_sample, container, false);
        LinearLayout sampleContainerView = (LinearLayout) view.findViewById(R.id.container_ll);
        bindViewClickListener(sampleContainerView);
        return view;
    }

    private void bindViewClickListener(ViewGroup group) {
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            group.getChildAt(i).setOnClickListener(mOnClickListener);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.merge:
                    mRxPresenter.merageOption();
                    break;
                case R.id.mergeDelayError:

                    break;
                case R.id.join:

                    break;
            }
        }
    };
}

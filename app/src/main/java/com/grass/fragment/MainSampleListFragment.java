package com.grass.fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.grass.core.base.fragment.BaseVertialListFragment;
import com.grass.core.event.EventOfOpenActivitySample;
import com.grass.data.AppSamplesStore;
import com.grass.recyclerview.fragment.MyItemRecyclerViewAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainSampleListFragment extends BaseVertialListFragment {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainSampleListFragment() {

    }

    @SuppressWarnings("unused")
    public static MainSampleListFragment newInstance() {
        MainSampleListFragment fragment = new MainSampleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initData(RecyclerView recyclerView) {
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(AppSamplesStore.getFragmentSamples()));
    }

    @Subscribe
    public void onEventOfOpenActivitySample(EventOfOpenActivitySample info) {
        Intent intent = new Intent(getContext(), info.getInfo().getSample());
        startActivity(intent);
    }
}

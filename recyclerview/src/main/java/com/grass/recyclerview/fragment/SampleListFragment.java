package com.grass.recyclerview.fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.example.anno.BindTestCase;
import com.grass.core.event.EventOfOpenActivitySample;
import com.grass.recyclerview.R;
import com.grass.recyclerview.fragment.data.SampleStore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@BindTestCase(type = 1, name = "SampleListFragment", des = "11111")
public class SampleListFragment extends Fragment {
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SampleListFragment() {

    }

    @SuppressWarnings("unused")
    public static SampleListFragment newInstance() {
        SampleListFragment fragment = new SampleListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(SampleStore.ITEMS));
        return view;
    }

    @Subscribe
    public void onEventOfOpenActivitySample(EventOfOpenActivitySample info) {
        Intent intent = new Intent(getContext(), info.getInfo().getSample());
        startActivity(intent);
    }
}

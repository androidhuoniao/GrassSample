package com.grass.fragment;

import com.grass.R;
import com.grass.module.SamplesStore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaopan.assemblyadapter.AssemblyRecyclerAdapter;

public class SampleListFragment extends Fragment {

    @Bind(R.id.recycleView)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        AssemblyRecyclerAdapter adapter = new AssemblyRecyclerAdapter(SamplesStore.getFragmentSamples());
        adapter.addItemFactory(new SampleListItemFactory(getActivity()));
        adapter.disableLoadMore();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new GrassDividerItemDecoration(getContext(),
                GrassDividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(adapter);
        return view;
    }



}

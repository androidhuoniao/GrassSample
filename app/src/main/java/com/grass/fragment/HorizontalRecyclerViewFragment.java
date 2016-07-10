package com.grass.fragment;

import com.grass.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HorizontalRecyclerViewFragment extends Fragment {

    RecyclerView mRecyclerView;

    public HorizontalRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horizontal_recyclerview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HoriAdapter());
        return view;
    }

    class HoriAdapter extends RecyclerView.Adapter<HoriViewHolder> {

        @Override
        public HoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_horizontal_recyclerview, null);
            return new HoriViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HoriViewHolder holder, int position) {
            holder.mContentTv.setText("grass: " + position);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class HoriViewHolder extends RecyclerView.ViewHolder {

        TextView mContentTv;

        public HoriViewHolder(View itemView) {
            super(itemView);
            mContentTv = (TextView) itemView.findViewById(R.id.contentTv);
        }
    }
}

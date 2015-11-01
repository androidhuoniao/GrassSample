package com.grass.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.grass.R;
import com.grass.recyclerview.ExtendViewholder;
import com.grass.recyclerview.OnRecyclerViewItemClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewFragment extends Fragment {


    @Bind(R.id.recycleView)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ItemData> mList;

    private OnFragmentInteractionListener mListener;

    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    public RecyclerViewFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        int index = 0;
        while (index < 30) {
            mList.add(new ItemData("" + index));
            index++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecyclerViewAdatper());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    private class RecyclerViewAdatper extends RecyclerView.Adapter<MyHolder> implements OnRecyclerViewItemClickListener {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(
                    getContext()).inflate(R.layout.item_recyclerview_list, parent,
                    false);
            MyHolder hodler = new MyHolder(view);
            hodler.setClickListener(this);
            return hodler;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.bindData(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public void onItemClick(View child, int position) {
            Toast.makeText(getContext(), "onItemCLick is working " + position, Toast.LENGTH_SHORT).show();
        }
    }

    class MyHolder extends ExtendViewholder {

        private TextView mContentTv;

        public MyHolder(View itemView) {
            super(itemView);
            mContentTv = (TextView) itemView.findViewById(R.id.contentTv);
        }

        public void bindData(ItemData item) {
            mContentTv.setText(item.getName());
        }
    }

    class ItemData {
        private String mName;

        public ItemData(String name) {
            mName = name;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }
    }
}

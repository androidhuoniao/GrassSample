package com.grass.fragment;

import com.example.anno.BindTestCase;
import com.grass.R;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@BindTestCase(type = 1, name = "DragFragment", des = "")
public class DragFragment extends Fragment {
    public DragFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drag, container, false);
        view.findViewById(R.id.dragview).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("1");
                ClipData data = new ClipData("1", new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return false;
            }
        });
        return view;
    }
}

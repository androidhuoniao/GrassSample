package com.grass.fragment;

import com.example.anno.BindTestCase;
import com.grass.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by grass on 16/8/16.
 */
@BindTestCase(type = 1, name = "SelectedTextViewFragmen", des = "自动排版")
public class SelectedTextViewFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_textview, container, false);
        return view;
    }
}

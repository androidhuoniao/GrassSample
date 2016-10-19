package com.grass.fragment;

import com.example.anno.BindTestCase;
import com.grass.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by grass on 16/8/16.
 */
@BindTestCase(type = 1, name = "MakeupFragment", des = "自动排版")
public class MakeupFragment extends Fragment {

    private EditText mContentEdt;
    private TextView mAutoMakeupTv;
    private TextView mValidateTv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_makeup, container, false);
        mContentEdt = (EditText) view.findViewById(R.id.content_edt);
        mAutoMakeupTv = (TextView) view.findViewById(R.id.makeup);
        mValidateTv = (TextView) view.findViewById(R.id.validate_tv);
        mValidateTv.setOnClickListener(mOnClickListener);
        mAutoMakeupTv.setOnClickListener(mOnClickListener);
        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.makeup:

                    break;
                case R.id.validate_tv:

                    break;
            }
        }
    };
}

package com.grass.fragment;

import com.grass.R;
import com.grass.module.BaseSampleItemInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItem;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItemFactory;

/**
 * Created by grass on 15/11/18.
 */
public class SampleListItemFactory extends AssemblyRecyclerItemFactory<SampleListItemFactory.SampleListItem> {

    @Override
    public Class<?> getBeanClass() {
        return BaseSampleItemInfo.class;
    }

    @Override
    public SampleListItem createAssemblyItem(ViewGroup viewGroup) {
        return new SampleListItem(viewGroup, this);
    }

    public static class SampleListItem extends AssemblyRecyclerItem<BaseSampleItemInfo, SampleListItemFactory> {

        @Bind(R.id.smapleName)
        private TextView mSampleNameTv;

        @Bind(R.id.sampleDescription)
        private TextView mSampleDesTv;

        protected SampleListItem(ViewGroup parent, SampleListItemFactory factory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false),
                    factory);
        }

        @Override
        protected void onFindViews(View convertView) {
            ButterKnife.bind(this, convertView);
        }

        @Override
        protected void onConfigViews(Context context) {
            // ... 你可以在这里注册一些点击事件并根据需要设置View的大小
        }

        @Override
        protected void onSetData(int position, BaseSampleItemInfo info) {
            mSampleNameTv.setText(info.getName());
            mSampleDesTv.setText(info.getDescription());

        }
    }
}




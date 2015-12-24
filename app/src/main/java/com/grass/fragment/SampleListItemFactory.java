package com.grass.fragment;

import java.util.ArrayList;

import com.grass.R;
import com.grass.event.EventOfChangeFragment;
import com.grass.model.BaseSampleItemInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItem;
import me.xiaopan.assemblyadapter.AssemblyRecyclerItemFactory;

/**
 * Created by grass on 15/11/18.
 */
public class SampleListItemFactory extends AssemblyRecyclerItemFactory<SampleListItemFactory.SampleListItem> {

    public SampleListItemFactory(Context context) {
    }

    @Override
    public Class<?> getBeanClass() {
        ArrayList<Object> objectArrayList = new ArrayList<>();
        return BaseSampleItemInfo.class;
    }

    @Override
    public SampleListItem createAssemblyItem(ViewGroup viewGroup) {
        return new SampleListItem(viewGroup, this);
    }

    public static class SampleListItem extends AssemblyRecyclerItem<BaseSampleItemInfo, SampleListItemFactory> {

        @Bind(R.id.smapleName)
        TextView mSampleNameTv;

        @Bind(R.id.sampleDescription)
        TextView mSampleDesTv;

        View mTopView;

        protected SampleListItem(ViewGroup parent, SampleListItemFactory factory){
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false),
                    factory);
        }

        @Override
        protected void onFindViews(View convertView) {
            ButterKnife.bind(this, convertView);
            mTopView = convertView;
        }

        @Override
        protected void onConfigViews(Context context) {
            // ... 你可以在这里注册一些点击事件并根据需要设置View的大小
            mTopView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EventOfChangeFragment(getData()));
                }
            });
        }

        @Override
        protected void onSetData(int position, BaseSampleItemInfo info) {
            mSampleNameTv.setText(info.getName());
            mSampleDesTv.setText(info.getDescription());

        }
    }

}




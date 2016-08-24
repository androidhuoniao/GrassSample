package com.grass.demo.retrofit.DoubanMovie;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.grass.core.base.fragment.BaseVertialListFragment;
import com.grass.core.base.mvp.MvpPresenter;
import com.grass.recyclerview.decoration.VerticalListDivider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

@BindTestCase(type = 1, name = "DoubanTop250Fragment", des = "获取豆瓣top250的电影")

public class DoubanTop250Fragment extends BaseVertialListFragment {

    public DoubanTop250Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpPresenter().attachView(this);
        getMvpPresenter().loadData();
    }

    @Override
    public void initRecycleView(RecyclerView recyclerView) {
        super.initRecycleView(recyclerView);
        Drawable dividerDrawable = getResources().getDrawable(R.drawable.vertical_divider);
        recyclerView.addItemDecoration(new VerticalListDivider(dividerDrawable));
    }

    @Override
    protected MvpPresenter createPresenter() {
        return new DouBanTop250Presenter();
    }
}

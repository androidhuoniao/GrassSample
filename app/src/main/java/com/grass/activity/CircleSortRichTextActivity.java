package com.grass.activity;

import java.util.ArrayList;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.grass.data.CommonContentItem;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by grass on 16/4/1.
 */

@BindTestCase(type = 0, name = "CircleSortRichTextActivity", des = "介绍如何使用drag list view")
public class CircleSortRichTextActivity extends Activity implements SlideAndDragListView.OnDragListener {

    private SlideAndDragListView mListView;

    private Menu mMenu;

    private ArrayList<CommonContentItem> mContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_sort_rich_text);
        mListView = (SlideAndDragListView) findViewById(R.id.draglistview);
        initMenu();
        initTestData();
        mListView.setMenu(mMenu);
        mListView.setAdapter(mAdapter);
        mListView.setOnDragListener(this, mContentList);
        mListView.setOnListItemLongClickListener(new SlideAndDragListView.OnListItemLongClickListener() {
            @Override
            public void onListItemLongClick(View view, int position) {

            }
        });
        mListView.closeSlidedItem();
    }

    private void initTestData() {
        mContentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommonContentItem commonContentItem = new CommonContentItem();
            commonContentItem.ctype = CommonContentItem.TYPE_TEXT;
            commonContentItem.value = "grass" + i;
            mContentList.add(commonContentItem);
        }
    }

    public void initMenu() {
        mMenu = new Menu(false, true);
        mMenu.addItem(new MenuItem.Builder().setWidth(
                100)
                .setText("One")
                .setTextColor(Color.GRAY)
                .setTextSize(14)
                .setBackground(new ColorDrawable(Color.parseColor("#fff2f2f2")))
                .build());
        mMenu.addItem(new MenuItem.Builder().setWidth(
                100)
                .setText("two")
                .setTextColor(Color.GRAY)
                .setTextSize(14)
                .setBackground(new ColorDrawable(Color.parseColor("#fff2f2f2")))
                .build());
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mContentList.size();

        }

        @Override
        public Object getItem(int position) {
            return mContentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                //加载布局
                convertView = LayoutInflater.from(CircleSortRichTextActivity.this).inflate(R.layout.item_rich_content,
                        null);
                //初始化控件
                vh.imgLogo = (ImageView) convertView.findViewById(R.id.customImg);
                vh.txtName = (TextView) convertView.findViewById(R.id.textcontent);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            //得到每一行的app相关信息
            CommonContentItem item = (CommonContentItem) this.getItem(position);
            if (item.ctype == CommonContentItem.TYPE_TEXT) {
                vh.txtName.setVisibility(View.VISIBLE);
                vh.imgLogo.setVisibility(View.GONE);
                vh.txtName.setText(item.value);

            } else if (item.ctype == CommonContentItem.TYPE_IMAGE) {
                vh.txtName.setVisibility(View.GONE);
                vh.imgLogo.setVisibility(View.VISIBLE);
            }

            return convertView;
        }
    };

    @Override
    public void onDragViewStart(int position) {

    }

    @Override
    public void onDragViewMoving(int position) {

    }

    @Override
    public void onDragViewDown(int position) {

    }

    private class ViewHolder {
        public ImageView imgLogo;
        public TextView txtName;
    }
}

package com.grass.activity.drag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.grass.data.CommonContentItem;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by grass on 16/4/1.
 */

@BindTestCase(type = 0, name = "DragNDropListActivity", des = "")
public class DragNDropListActivity extends Activity {

    private DragNDropListView mListView;

    private ArrayList<CommonContentItem> mContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_ndrop_listview);
        mListView = (DragNDropListView) findViewById(R.id.draglistview);
//        initTestData();
//        mListView.setAdapter(new DragAdapter(this, mContentList));
//        mListView.setDraggingEnabled(true);

        ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < 30; ++i) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", "item" + i);
            item.put("_id", i);

            items.add(item);
        }

        mListView.setDragNDropAdapter(new DragNDropSimpleAdapter(this,
                items,
                R.layout.testitem,
                new String[]{"name"},
                new int[]{R.id.text},
                R.id.tophandler));
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

}

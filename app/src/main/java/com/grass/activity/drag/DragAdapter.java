package com.grass.activity.drag;

import java.util.ArrayList;

import com.grass.R;
import com.grass.data.CommonContentItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by grass on 16/7/28.
 */
public class DragAdapter extends BaseAdapter implements DragNDropAdapter {

    private ArrayList<CommonContentItem> mContentList;
    private int mPosition[];
    private Context mContext;

    public DragAdapter(Context context, ArrayList<CommonContentItem> list) {
        mContentList = list;
        mContext = context;
        setup(mContentList.size());
    }

    private void setup(int size) {
        mPosition = new int[size];

        for (int i = 0; i < size; ++i) {
            mPosition[i] = i;
        }
    }

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_rich_content,
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

    @Override
    public int getDragHandler() {
        return R.id.containerHandler;
    }

    @Override
    public void onItemDrag(DragNDropListView parent, View view, int position, long id) {

    }

    @Override
    public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id) {
        int position = mPosition[startPosition];

        if (startPosition < endPosition) {
            for (int i = startPosition; i < endPosition; ++i) {
                mPosition[i] = mPosition[i + 1];
            }
        } else if (endPosition < startPosition) {
            for (int i = startPosition; i > endPosition; --i) {
                mPosition[i] = mPosition[i - 1];
            }
        }

        mPosition[endPosition] = position;
    }

    @Override
    public void notifyDataSetChanged() {
        setup(mContentList.size());
        super.notifyDataSetChanged();
    }

    private class ViewHolder {
        public ImageView imgLogo;
        public TextView txtName;
    }
}

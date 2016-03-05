package com.grass.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by grass on 15/11/22.
 */
public class HorizontalRecyclerViewDivider extends RecyclerView.ItemDecoration {

    private Drawable mDividerDrawable;

    public HorizontalRecyclerViewDivider(Drawable dividerDrawable) {
        mDividerDrawable = dividerDrawable;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getTop();
        int bottom = parent.getBottom();
        int paddingTop = parent.getPaddingTop();
        int paddingBottom = parent.getPaddingBottom();
        int topEdge = top + paddingTop;
        int bottomEdge = bottom - paddingBottom;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int leftEdge = child.getRight() + params.rightMargin;
            final int rightEdge = leftEdge + mDividerDrawable.getIntrinsicWidth();
            mDividerDrawable.setBounds(leftEdge, topEdge, rightEdge, bottomEdge);
            mDividerDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, mDividerDrawable.getIntrinsicWidth(), 0);
    }
}

package com.grass.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * Created by grass on 16/8/24.
 */

public class CustomSeekBar extends SeekBar {

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        int thumbOffset = getThumbOffset() + 3;
        super.onDraw(canvas);
        float[] cxarray = new float[] {thumbOffset, getWidth() / 2, getWidth() - thumbOffset};
        float cy = getHeight() / 2;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        for (int i = 0; i < cxarray.length; i++) {
            canvas.drawCircle(cxarray[i], cy, 20, paint);
        }
    }
}

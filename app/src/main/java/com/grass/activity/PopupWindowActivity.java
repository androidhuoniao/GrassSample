package com.grass.activity;

import com.example.anno.BindTestCase;
import com.grass.R;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

@BindTestCase(type = 0, name = "PopupWindowActivity", des = "实现popupWindow的动画")
public class PopupWindowActivity extends AppCompatActivity {

    Button mBottomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mBottomBtn = (Button) findViewById(R.id.showbottomwindow);
        mBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = new TextView(PopupWindowActivity.this);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(1080, 600);
                tv.setLayoutParams(layoutParams);
                tv.setBackgroundColor(Color.RED);

                PopupWindow popupWindow = new PopupWindow(tv,
                        WindowManager.LayoutParams.MATCH_PARENT, 600);
                // 实例化一个ColorDrawable颜色为半透明
                ColorDrawable dw = new ColorDrawable(0xb0000000);
                popupWindow.setBackgroundDrawable(dw);

                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1f);
                    }
                });

                popupWindow.showAtLocation(mBottomBtn, Gravity.BOTTOM, 0, 0);
            }

        });

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}

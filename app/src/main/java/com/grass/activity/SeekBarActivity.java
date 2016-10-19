package com.grass.activity;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

@BindTestCase(type = 0, name = "SeekBarActivity", des = "实现知乎拖动改变字体大小的进度条")
public class SeekBarActivity extends AppCompatActivity {

    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Logger.i("onProgressChanged " + progress + " fromUser: " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Logger.i("onStartTrackingTouch ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Logger.i("StopTrackingTouch");
                int toProgress = computeActualProgress(seekBar);
                seekBar.setProgress(toProgress);
            }
        });

    }

    private int computeActualProgress(SeekBar seekBar) {
        int maxProgress = seekBar.getMax();
        int progress = seekBar.getProgress();
        int middle = maxProgress / 2;
        int toProgress = 0;
        if (progress <= middle) {
            int offsetA = progress;
            int offsetB = Math.abs(progress - middle);
            if (offsetA < offsetB) {
                toProgress = 0;
            } else {
                toProgress = middle;
            }
        } else if (progress > middle && progress <= maxProgress) {
            int offsetA = progress - middle;
            int offsetB = Math.abs(progress - maxProgress);
            if (offsetA < offsetB) {
                toProgress = middle;
            } else {
                toProgress = maxProgress;
            }
        }
        return toProgress;
    }
}

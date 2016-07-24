package com.grass;

import com.example.BindingItemStartup;
import com.example.BindingTestStartup;

import android.app.Application;

/**
 * Created by grass on 16/7/24.
 */

public class GrassApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new BindingItemStartup();
        new BindingTestStartup();
    }
}


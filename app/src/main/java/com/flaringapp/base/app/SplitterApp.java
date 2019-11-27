package com.flaringapp.base.app;

import android.app.Application;

import com.flaringapp.base.app.di.Di;

public class SplitterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Di.initModules();
    }
}

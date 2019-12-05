package com.flaringapp.kursach.app;

import android.app.Application;

import com.flaringapp.kursach.app.di.Di;

public class SplitterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Di.initModules();
    }
}

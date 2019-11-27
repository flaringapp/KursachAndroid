package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.app.di.DiModule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public final class DataModule extends DiModule {

    public DataModule() {
        super();
    }

    @Override
    public List<Object> provideSingles() {
        return Arrays.asList(

        );
    }

    @Override
    public HashMap<Class, Callable<Object>> provideFactories() {
        return new HashMap<Class, Callable<Object>>() {

        };
    }
}

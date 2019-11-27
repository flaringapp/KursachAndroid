package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.app.di.DiModule;
import com.flaringapp.base.data.treeSplitter.impl.TextTreeSplitterImpl;
import com.flaringapp.base.data.treeSplitter.impl.validators.SplitterValidator;
import com.flaringapp.base.data.treeSplitter.impl.validators.SplitterValidatorImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public final class DataModule extends DiModule {

    public DataModule() {
        super();
    }

    @Override
    protected List<Object> provideSingles() {
        return Arrays.asList(
                new TextTreeSplitterImpl(get(SplitterValidator.class)),
                new SplitterValidatorImpl()
        );
    }

    @Override
    protected HashMap<Class, Callable<Object>> provideFactories() {
        return new HashMap<Class, Callable<Object>>() {

        };
    }
}

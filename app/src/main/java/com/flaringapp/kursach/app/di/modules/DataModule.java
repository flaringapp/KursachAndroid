package com.flaringapp.kursach.app.di.modules;

import com.flaringapp.kursach.data.treeSplitter.impl.TextTreeSplitterImpl;
import com.flaringapp.kursach.data.treeSplitter.impl.validator.SplitterValidator;
import com.flaringapp.kursach.data.treeSplitter.impl.validator.impl.SplitterValidatorImpl;

public final class DataModule extends DiModule {

    @Override
    protected void initSingles() {
        addSingle(
                new SplitterValidatorImpl()
        );

        addSingle(
                new TextTreeSplitterImpl(get(SplitterValidator.class))
        );
    }
}

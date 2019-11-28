package com.flaringapp.base.app.di.modules;

import com.flaringapp.base.data.treeSplitter.impl.TextTreeSplitterImpl;
import com.flaringapp.base.data.treeSplitter.impl.validator.SplitterValidator;
import com.flaringapp.base.data.treeSplitter.impl.validator.SplitterValidatorImpl;

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

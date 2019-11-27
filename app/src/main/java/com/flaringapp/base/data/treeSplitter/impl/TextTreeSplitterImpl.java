package com.flaringapp.base.data.treeSplitter.impl;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;
import com.flaringapp.base.data.treeSplitter.impl.validators.SplitterValidator;

public class TextTreeSplitterImpl implements TextTreeSplitter {

    private SplitterValidator splitterValidator;

    public TextTreeSplitterImpl(SplitterValidator splitterValidator) {
        this.splitterValidator = splitterValidator;
    }

    @Override
    public ISplitNode<String> split(
            String text,
            String startSeparator,
            String endSeparator
    ) throws SplitterException {
        splitterValidator.validateParams(text, startSeparator, endSeparator);

        return null;
    }
}

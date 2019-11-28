package com.flaringapp.base.data.treeSplitter.impl;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter;
import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;
import com.flaringapp.base.data.treeSplitter.impl.validator.SplitterValidator;
import com.flaringapp.base.data.treeSplitter.models.SplitNode;

public class TextTreeSplitterImpl implements TextTreeSplitter {

    private SplitterValidator splitterValidator;

    public TextTreeSplitterImpl(SplitterValidator splitterValidator) {
        this.splitterValidator = splitterValidator;
    }

    @Override
    public ISplitNode split(
            String text,
            String startSeparator,
            String endSeparator
    ) throws SplitterException {
        splitterValidator.validateParams(text, startSeparator, endSeparator);

        SplitNode rootNode = new SplitNode();

        for (int i = 0; i < text.length(); i++) {
            if (isSeparatorAtIndex(text, startSeparator, i)) {
                rootNode.levelDown();
                rootNode.appendSeparatorSymbol(text.charAt(i));
                i += startSeparator.length() - 1;
            } else if (isSeparatorAtIndex(text, endSeparator, i)) {
                rootNode.appendSeparatorSymbol(text.charAt(i));
                rootNode.levelUp();
                i += startSeparator.length() - 1;
            } else {
                rootNode.appendSymbol(text.charAt(i));
            }
        }

        return rootNode;
    }

    private boolean isSeparatorAtIndex(String text, String separator, int index) {
        if (index + separator.length() - 1 >= text.length()) return false;

        for (int i = 0; i < separator.length(); i++) {
            if (text.charAt(index + i) != separator.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

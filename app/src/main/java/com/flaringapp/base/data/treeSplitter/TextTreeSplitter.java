package com.flaringapp.base.data.treeSplitter;

import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;

import java.util.List;

public interface TextTreeSplitter {

    interface ISplitNode {
        String getData();

        List<ISplitNode> childNodes();

        void appendSymbol(char symbol);
        void appendSeparatorSymbol(char separatorSymbol);
        void levelDown();
        void levelUp();
    }

    ISplitNode split(
            String text,
            String startSeparator,
            String endSeparator
    ) throws SplitterException;

}

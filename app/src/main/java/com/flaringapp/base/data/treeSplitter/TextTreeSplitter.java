package com.flaringapp.base.data.treeSplitter;

import java.util.List;

import io.reactivex.Single;

public interface TextTreeSplitter {

    interface ISplitNode {
        String getData();

        List<ISplitNode> childNodes();

        void appendSymbol(char symbol);
        void appendSeparatorSymbol(char separatorSymbol);
        void levelDown();
        void levelUp();
    }

    Single<ISplitNode> split(
            String text,
            String startSeparator,
            String endSeparator
    );

}

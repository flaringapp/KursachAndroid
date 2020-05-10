package com.flaringapp.kursach.data.treeSplitter;

import com.flaringapp.treeview.ISplitNodeData;

import java.util.List;

import io.reactivex.Single;

public interface TextTreeSplitter {

    interface ISplitNode extends ISplitNodeData {
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

package com.flaringapp.kursach.data.treeSplitter;

import java.util.List;

import io.reactivex.Single;

public interface TextTreeSplitter {

    interface ISplitNode {
        String getData();

        List<ISplitNode> childNodes();
    }

    Single<ISplitNode> split(
            String text,
            String startSeparator,
            String endSeparator
    );

}

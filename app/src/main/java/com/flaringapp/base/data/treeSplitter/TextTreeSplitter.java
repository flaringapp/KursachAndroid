package com.flaringapp.base.data.treeSplitter;

import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;

import java.util.LinkedList;

public interface TextTreeSplitter {

    interface ISplitNode<T>
    {
        T getData();

        LinkedList<ISplitNode<T>> childNodes();
    }

    ISplitNode<String> split(
            String text,
            String startSeparator,
            String endSeparator
    ) throws SplitterException;

}

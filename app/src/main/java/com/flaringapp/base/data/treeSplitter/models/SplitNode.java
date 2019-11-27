package com.flaringapp.base.data.treeSplitter.models;

import com.flaringapp.base.data.treeSplitter.TextTreeSplitter.ISplitNode;

import java.util.LinkedList;

public class SplitNode<T> implements ISplitNode<T> {

    private final T data;
    private final LinkedList<ISplitNode<T>> childNodes;

    public SplitNode(T data) {
        this.data = data;
        this.childNodes = new LinkedList<>();
    }

    public SplitNode(T data, LinkedList<ISplitNode<T>> childNodes) {
        this.data = data;
        this.childNodes = childNodes;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public LinkedList<ISplitNode<T>> childNodes() {
        return childNodes;
    }
}

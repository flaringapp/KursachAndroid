package com.flaringapp.base.data.treeSplitter.exceptions;

public class EmptySeparatorException extends SplitterException {

    public EmptySeparatorException() {
        super("Separator can't be empty!");
    }
}

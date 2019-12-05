package com.flaringapp.kursach.data.treeSplitter.exceptions;

public class EmptyTextException extends SplitterException {

    public EmptyTextException() {
        super("Text can't be split because it's empty!");
    }
}

package com.flaringapp.base.data.treeSplitter.exceptions;

public class InvalidSeparatorsCountException extends SplitterException {

    public InvalidSeparatorsCountException(int startSeparatorCount, int endSeparatorCount) {
        super("Open separators count (" + startSeparatorCount + ") " +
                "is not equal to close ones (" + endSeparatorCount + ")!");
    }
}

package com.flaringapp.kursach.data.treeSplitter.exceptions;

public class InvalidSeparatorsCountException extends SplitterException {

    private int startSeparatorCount;
    private int endSeparatorCount;

    public InvalidSeparatorsCountException(int startSeparatorCount, int endSeparatorCount) {
        super("Start separators count (" + startSeparatorCount + ") " +
                "is not equal to end ones (" + endSeparatorCount + ")!");

        this.startSeparatorCount = startSeparatorCount;
        this.endSeparatorCount = endSeparatorCount;
    }

    public int getStartSeparatorCount() {
        return startSeparatorCount;
    }

    public int getEndSeparatorCount() {
        return endSeparatorCount;
    }
}

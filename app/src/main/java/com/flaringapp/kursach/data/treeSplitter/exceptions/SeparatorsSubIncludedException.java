package com.flaringapp.kursach.data.treeSplitter.exceptions;

public class SeparatorsSubIncludedException extends SplitterException {

    private final String separatorMain;
    private final String separatorSubIncluded;

    public SeparatorsSubIncludedException(String separatorMain, String separatorSubIncluded) {
        super("Separator (" + separatorSubIncluded + ") is part of another separator (" +
                separatorMain + ")");
        this.separatorMain = separatorMain;
        this.separatorSubIncluded = separatorSubIncluded;
    }

    public String getSeparatorMain() {
        return separatorMain;
    }

    public String getSeparatorSubIncluded() {
        return separatorSubIncluded;
    }
}

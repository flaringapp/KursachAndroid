package com.flaringapp.kursach.data.treeSplitter.exceptions;

public class SameSeparatorsException extends SplitterException {

    private String separator;

    public SameSeparatorsException(String separator) {
        super("Separators can't be the same! (" + separator + ")");
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}

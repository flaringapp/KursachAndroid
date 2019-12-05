package com.flaringapp.kursach.data.treeSplitter.exceptions;

public class InvalidSeparatorsOrderException extends SplitterException {

    public InvalidSeparatorsOrderException() {
        super("Separators order in your text is not correct! Closing separator on level 0 is before opening");
    }
}

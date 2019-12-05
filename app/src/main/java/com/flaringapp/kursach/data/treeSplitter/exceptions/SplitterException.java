package com.flaringapp.kursach.data.treeSplitter.exceptions;

import androidx.annotation.Nullable;

public abstract class SplitterException extends Exception {
    private String message;

    SplitterException(String message) {
        this.message = message;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}

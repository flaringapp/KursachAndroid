package com.flaringapp.base.app.di.exceptions;

import androidx.annotation.Nullable;

public class MissingDependencyException extends Exception {

    private final String className;

    public MissingDependencyException(String className) {
        this.className = className;
    }

    @Nullable
    @Override
    public String getMessage() {
        return "Missing dependency for class " + className;
    }
}

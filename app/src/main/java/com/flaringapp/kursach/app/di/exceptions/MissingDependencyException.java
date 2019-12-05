package com.flaringapp.kursach.app.di.exceptions;

import androidx.annotation.Nullable;

public class MissingDependencyException extends RuntimeException {

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

package com.flaringapp.kursach.data.treeSplitter.impl.validator;

import com.flaringapp.kursach.data.treeSplitter.exceptions.SplitterException;

public interface SplitterValidator {

    void validateParams(
            String text,
            String startSymbol,
            String endSymbol
    ) throws SplitterException;

}

package com.flaringapp.base.data.treeSplitter.impl.validators;

import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;

public interface SplitterValidator {

    void validateParams(
            String text,
            String startSymbol,
            String endSymbol
    ) throws SplitterException;

}

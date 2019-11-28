package com.flaringapp.base.data.treeSplitter.impl.validator;

import android.text.TextUtils;

import com.flaringapp.base.data.treeSplitter.exceptions.EmptySeparatorException;
import com.flaringapp.base.data.treeSplitter.exceptions.EmptyTextException;
import com.flaringapp.base.data.treeSplitter.exceptions.InvalidSeparatorsCountException;
import com.flaringapp.base.data.treeSplitter.exceptions.SplitterException;

public class SplitterValidatorImpl implements SplitterValidator {

    @Override
    public void validateParams(
            String text,
            String startSeparator,
            String endSeparator
    ) throws SplitterException {
        validateText(text);

        validateSeparator(startSeparator);
        validateSeparator(endSeparator);

        validateSeparatorsCountInText(text, startSeparator, endSeparator);
    }

    private void validateText(String text) throws EmptyTextException {
        if (TextUtils.isEmpty(text)) throw new EmptyTextException();
    }

    private void validateSeparator(String separator) throws EmptySeparatorException {
        if (TextUtils.isEmpty(separator)) throw new EmptySeparatorException();
    }

    private void validateSeparatorsCountInText(
            String text,
            String startSeparator,
            String endSeparator
    ) throws InvalidSeparatorsCountException {
        int startSeparatorCount = getSeparatorInTextCount(text, startSeparator);
        int endSeparatorCount = getSeparatorInTextCount(text, endSeparator);

        if (startSeparatorCount != endSeparatorCount) {
            throw new InvalidSeparatorsCountException(
                    startSeparatorCount,
                    endSeparatorCount
            );
        }
    }

    private int getSeparatorInTextCount(String text, String separator) {
        int separatorCount = 0;

        int indexOfLastSeparator = text.indexOf(separator);

        while (indexOfLastSeparator != -1) {
            indexOfLastSeparator = text.indexOf(
                    separator,
                    indexOfLastSeparator + separator.length()
            );

            separatorCount++;
        }

        return separatorCount;
    }
}

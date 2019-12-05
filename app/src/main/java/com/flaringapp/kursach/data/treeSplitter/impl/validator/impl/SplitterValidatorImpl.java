package com.flaringapp.kursach.data.treeSplitter.impl.validator.impl;

import android.text.TextUtils;

import com.flaringapp.kursach.data.treeSplitter.exceptions.EmptySeparatorException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.EmptyTextException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.InvalidSeparatorsCountException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.InvalidSeparatorsOrderException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.SameSeparatorsException;
import com.flaringapp.kursach.data.treeSplitter.exceptions.SplitterException;
import com.flaringapp.kursach.data.treeSplitter.impl.validator.SplitterValidator;
import com.flaringapp.kursach.data.treeSplitter.utils.SplitterUtils;

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

        validateSameSeparators(startSeparator, endSeparator);

        validateSeparatorsCountInText(text, startSeparator, endSeparator);

        validateSeparatorsOrderInText(text, startSeparator, endSeparator);
    }

    private void validateText(String text) throws EmptyTextException {
        if (TextUtils.isEmpty(text)) throw new EmptyTextException();
    }

    private void validateSeparator(String separator) throws EmptySeparatorException {
        if (TextUtils.isEmpty(separator)) throw new EmptySeparatorException();
    }

    private void validateSameSeparators(String startSeparator, String endSeparator) throws SameSeparatorsException {
        if (startSeparator.equals(endSeparator)) {
            throw new SameSeparatorsException(startSeparator);
        }
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

    private void validateSeparatorsOrderInText(
            String text,
            String separatorStart,
            String separatorEnd
    ) throws InvalidSeparatorsOrderException {
        int currentLevel = 0;

        for (int i = 0; i < text.length(); i++) {
            if (SplitterUtils.isSeparatorAtIndex(text, separatorStart, i)) {
                currentLevel++;
                i += separatorStart.length() - 1;
            } else if (SplitterUtils.isSeparatorAtIndex(text, separatorEnd, i)) {
                currentLevel--;
                i += separatorEnd.length() - 1;
            }

            if (currentLevel < 0) {
                throw new InvalidSeparatorsOrderException();
            }
        }
    }
}

package com.flaringapp.base.data.treeSplitter.utils;

public class SplitterUtils {

    public static boolean isSeparatorAtIndex(String text, String separator, int index) {
        if (index + separator.length() - 1 >= text.length()) return false;

        for (int i = 0; i < separator.length(); i++) {
            if (text.charAt(index + i) != separator.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

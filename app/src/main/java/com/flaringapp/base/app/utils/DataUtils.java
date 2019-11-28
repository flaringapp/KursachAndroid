package com.flaringapp.base.app.utils;

import java.util.Iterator;
import java.util.List;

public final class DataUtils {

    public static int[] intArrayOf(List<Integer> data) {
        int[] array = new int[data.size()];

        for (int i = 0; i < data.size(); i++) {
            array[i] = data.get(i);
        }

        return array;
    }

}

package io.vn.nguyenduck.auto_generate_api.utils;

import java.util.ArrayList;
import java.util.List;

public final class Utils {
    public static String toSnakeCase(String s) {
        return s.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    public static List<Integer> createRangeArray(int max) {
        return createRangeArray(max, 1);
    }

    public static List<Integer> createRangeArray(int max, int distance) {
        return createRangeArray(0, max, distance);
    }

    public static List<Integer> createRangeArray(int min, int max, int distance) {
        List<Integer> list = new ArrayList();

        for(int i = min; i <= max; i += distance) {
            list.add(i);
        }

        return list;
    }
}
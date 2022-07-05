package com.sudoku.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldUtilz {
    private static final Map<Integer, List<Integer>> cubeMap = initCubes();
    private static final Map<Integer, List<Integer>> rowMap = initRows();
    private static final Map<Integer, List<Integer>> colMap = initCols();

    /**
     * Position of 9 sub fields that should contain unique numbers
     *
     * @return map of cubes with positions
     */
    private static Map<Integer, List<Integer>> initCubes() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 1, 2, 9, 10, 11, 18, 19, 20));
        map.put(1, List.of(3, 4, 5, 12, 13, 14, 21, 22, 23));
        map.put(2, List.of(6, 7, 8, 15, 16, 17, 24, 25, 26));
        map.put(3, List.of(27, 28, 29, 36, 37, 38, 45, 46, 47));
        map.put(4, List.of(30, 31, 32, 39, 40, 41, 48, 49, 50));
        map.put(5, List.of(33, 34, 35, 42, 43, 44, 51, 52, 53));
        map.put(6, List.of(54, 55, 56, 63, 64, 65, 72, 73, 74));
        map.put(7, List.of(57, 58, 59, 66, 67, 68, 75, 76, 77));
        map.put(8, List.of(60, 61, 62, 69, 70, 71, 78, 79, 80));
        return map;
    }

    private static Map<Integer, List<Integer>> initRows() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        map.put(1, List.of(9, 10, 11, 12, 13, 14, 15, 16, 17));
        map.put(2, List.of(18, 19, 20, 21, 22, 23, 24, 25, 26));
        map.put(3, List.of(27, 28, 29, 30, 31, 32, 33, 34, 35));
        map.put(4, List.of(36, 37, 38, 39, 40, 41, 42, 43, 44));
        map.put(5, List.of(45, 46, 47, 48, 49, 50, 51, 52, 53));
        map.put(6, List.of(54, 55, 56, 57, 58, 59, 60, 61, 62));
        map.put(7, List.of(63, 64, 65, 66, 67, 68, 69, 70, 71));
        map.put(8, List.of(72, 73, 74, 75, 76, 77, 78, 79, 80));
        return map;
    }

    private static Map<Integer, List<Integer>> initCols() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 9, 18, 27, 36, 45, 54, 63, 72));
        map.put(1, List.of(1, 10, 19, 28, 37, 46, 55, 64, 73));
        map.put(2, List.of(2, 11, 20, 29, 38, 47, 56, 65, 74));
        map.put(3, List.of(3, 12, 21, 30, 39, 48, 57, 66, 75));
        map.put(4, List.of(4, 13, 22, 31, 40, 49, 58, 67, 76));
        map.put(5, List.of(5, 14, 23, 32, 41, 50, 59, 68, 77));
        map.put(6, List.of(6, 15, 24, 33, 42, 51, 60, 69, 78));
        map.put(7, List.of(7, 16, 25, 34, 43, 52, 61, 70, 79));
        map.put(8, List.of(8, 17, 26, 35, 44, 53, 62, 71, 80));
        return map;
    }

    public static int getCubeIDbyPosition(int pos) {
        for (Map.Entry<Integer, List<Integer>> entry : cubeMap.entrySet()) {
            if (entry.getValue().contains(pos)) return entry.getKey();
        }
        return -1;
    }

    public static int getRowIDbyPosition(int pos) {
        for (Map.Entry<Integer, List<Integer>> entry : rowMap.entrySet()) {
            if (entry.getValue().contains(pos)) return entry.getKey();
        }
        return -1;
    }

    public static int getSliceIDbyPosition(int pos) {
        for (Map.Entry<Integer, List<Integer>> entry : colMap.entrySet()) {
            if (entry.getValue().contains(pos)) return entry.getKey();
        }
        return -1;
    }

    public static List<Integer> getCubePositions(int cubeOrder) {
        return cubeMap.getOrDefault(cubeOrder, List.of());
    }

    public static List<Integer> getRowPositions(int rowOrder) {
        return rowMap.getOrDefault(rowOrder, List.of());
    }

    public static List<Integer> getSlicePositions(int colOrder) {
        return colMap.getOrDefault(colOrder, List.of());
    }
}

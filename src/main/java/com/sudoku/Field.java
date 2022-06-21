package com.sudoku;

import com.sudoku.logger.ConsoleLogger;
import com.sudoku.properties.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Field {
    public static final int FIELD_CAPACITY = 81;
    public static final int DIM_SIZE = 9;
    private static final int STRING_CAPACITY = 320;
    private static Field field;
    private int[][] sudokuFields = new int[DIM_SIZE][DIM_SIZE];
    private static final ConsoleLogger logger = ConsoleLogger.getInstance();
    private Map<Integer, List<Integer>> cubeMap;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setFailedStatus() {
        status = Status.FAILED;
    }

    public void setValidatedStatus() {
        status = Status.VALIDATED;
    }

    public void setSolvedStatus() {
        status = Status.SOLVED;
    }

    /**
     * hidden constructor
     */
    private Field() {
        cubeMap = initCubes();
    }

    /**
     * singleton instance
     *
     * @return Field instance
     */
    public static synchronized Field getInstance() {
        if (field == null) field = new Field();
        return field;
    }

    /**
     * For test, assertions on sudoku fields
     *
     * @return two dim. integer array
     */
    public int[][] getSudokuFields() {
        return sudokuFields;
    }

    /**
     * Print out sudoku fields
     */
    public void showFields() {
        for (int j = 0, sudokuFieldLength = sudokuFields.length; j < sudokuFieldLength; j++) {
            int[] arr = sudokuFields[j];
            StringBuilder output = new StringBuilder(STRING_CAPACITY);
            for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
                if (i == 0) {
                    output.append("[ ").append(arr[i]);
                } else if (i == arrLength - 1) {
                    output.append(' ').append(arr[i]).append(" ]");
                } else if ((i + 1) % 3 == 0) {
                    output.append(' ').append(arr[i]).append(" ]  [");
                } else output.append(' ').append(arr[i]);
            }
            logger.toConsole(output.toString().replace("0", "*"));
            if ((j + 1) % 3 == 0) logger.toConsole("");
        }
    }

    /**
     * Set sudoku field using rows and columns
     * Zero is hidden value
     *
     * @param row   row number with zero base
     * @param col   column number with base zero
     * @param value value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    private boolean setField(int row, int col, int value) {
        if (row < 0 || row >= DIM_SIZE) return false;
        if (col < 0 || col >= DIM_SIZE) return false;
        if (value < 0 || value > DIM_SIZE) return false;
        try {
            sudokuFields[row][col] = value;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Set sudoku field using position from 0 to 80
     * <p>
     * [ 0,  1,  2  ] [ 3,  4,  5  ]  [ 6,  7,  8  ]
     * [ 9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]
     * [ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]
     * <p>
     * [ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]
     * [ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]
     * [ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]
     * <p>
     * [ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]
     * [ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]
     * [ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]
     *
     * @param position absolute position in sudoku field
     * @param value    value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    public boolean setField(int position, int value) {
        int row = position / DIM_SIZE;
        int col = position - (row * DIM_SIZE);
        return setField(row, col, value);
    }

    public int getField(int position){
        int row = position / DIM_SIZE;
        int col = position - (row * DIM_SIZE);
        return sudokuFields[row][col];
    }

    public List<Integer> getCubePositions(int cubeOrder){
        return cubeMap.getOrDefault(cubeOrder, List.of());
    }

    /**
     * Set sudoku field from command line
     * use 0 as hidden value, if values < 80 all remaining are 0
     *
     */
    public void setFields(List<Integer> values) {
        for (int i = 0; i < FIELD_CAPACITY; i++) {
            setField(i, values.get(i));
        }
    }

    /**
     * initialize fields
     */
    public void resetFields() {
        sudokuFields = new int[DIM_SIZE][DIM_SIZE];
    }

    private Map<Integer, List<Integer>> initCubes(){
        Map<Integer, List<Integer>> cubeMap = new HashMap<>();
        cubeMap.put(0, List.of(0,1,2,9,10,11,18,19,20));
        cubeMap.put(1, List.of(3,4,5,12,13,14,21,22,23));
        cubeMap.put(2, List.of(6,7,8,15,16,17,24,25,26));
        cubeMap.put(3, List.of(27,28,29,36,37,38,45,46,47));
        cubeMap.put(4, List.of(30,31,32,39,40,41,48,49,50));
        cubeMap.put(5, List.of(33,34,35,42,43,44,51,52,53));
        cubeMap.put(6, List.of(54,55,56,63,64,65,72,73,74));
        cubeMap.put(7, List.of(57,58,59,66,67,68,75,76,77));
        cubeMap.put(8, List.of(60,61,62,69,70,71,78,79,80));
        return cubeMap;
    }
}

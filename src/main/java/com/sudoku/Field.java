package com.sudoku;

import com.sudoku.logger.ConsoleLogger;

public final class Field {
    private static final int SIZE     = 9;
    private static final int CAPACITY = 320;
    private static Field  field;
    private int[][] sudokuFields = new int[SIZE][SIZE];
    private static final ConsoleLogger logger = ConsoleLogger.getInstance();

    /**
     * hidden constructor
     */
    private Field(){}

    /**
     * singleton instance
     * @return Field instance
     */
    public static synchronized Field getInstance(){
        if (field == null) field = new Field();
        return field;
    }

    /**
     * For test, assertions on sudoku fields
     * @return two dim. integer array
     */
    public int[][] getSudokuFields(){
        return sudokuFields;
    }

    /**
     * Print out sudoku fields
     */
    public void showFields(){
        for (int j = 0, sudokuFieldLength = sudokuFields.length; j < sudokuFieldLength; j++) {
            int[] arr = sudokuFields[j];
            StringBuilder output = new StringBuilder(CAPACITY);
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

//    public void setFields(int[][] fields){
//        sudokuField = fields;
//    }

    /**
     * Set sudoku field using rows and columns
     * Zero is hidden value
     * @param row row number with zero base
     * @param col column number with base zero
     * @param value value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    public boolean setField(int row, int col, int value){
        if (row < 0 || row >= SIZE) return false;
        if (col < 0 || col >= SIZE) return false;
        if (value < 0 || value > SIZE ) return false;
        try {
            sudokuFields[row][col] = value;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Set sudoku field using position from 0 to 80
     *
     * [ 0,  1,  2  ] [ 3,  4,  5  ]  [ 6,  7,  8  ]
     * [ 9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]
     * [ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]
     *
     * [ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]
     * [ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]
     * [ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]
     *
     * [ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]
     * [ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]
     * [ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]
     *
     * @param position absolute position in sudoku field
     * @param value value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    public boolean setField(int position, int value){
        int row = position / SIZE;
        int col = position - (row * SIZE);
        return setField(row, col, value);
    }

    /**
     * initialize fields
     */
    public void resetFields(){
        sudokuFields = new int[SIZE][SIZE];
    }
}

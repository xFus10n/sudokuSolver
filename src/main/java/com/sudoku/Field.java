package com.sudoku;

import org.apache.log4j.Logger;

public class Field {
    private static final int SIZE = 9;
    public static final int CAPACITY  = 320;
    private static Field field;
    private final int[][] sudokuField = new int[SIZE][SIZE];

    private Field(){}

    public static Field getInstance(){
        if (field == null) field = new Field();
        return field;
    }

    public void showFields(Logger logger){
        for (int j = 0, sudokuFieldLength = sudokuField.length; j < sudokuFieldLength; j++) {
            int[] arr = sudokuField[j];
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
            logger.info(output.toString());
            if ((j + 1) % 3 == 0) logger.info("");
        }
    }

//    public void setFields(int[][] fields){
//        sudokuField = fields;
//    }

    public boolean setField(int row, int col, int value){
        if (row < 0 || row >= SIZE) return false;
        if (col < 0 || col >= SIZE) return false;
        try {
            sudokuField[row][col] = value;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean setField(int position, int value){
        int row = position / SIZE;
        int col = position - (row * SIZE);
        return setField(row, col, value);
    }
}

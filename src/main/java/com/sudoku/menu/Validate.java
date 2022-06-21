package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.*;

public class Validate implements Action {
    private boolean hasHiddenElements;

    @Override
    public int id() {
        return 5;
    }

    @Override
    public String name() {
        return "validate sudoku field";
    }

    @Override
    public void execute(Field sudokuField) {
        hasHiddenElements = setHasHiddenElements(sudokuField);
        ConsoleLogger.getInstance()
                     .toConsole("Has hidden elements = " + hasHiddenElements);

        boolean rowCheck = rowCheck(sudokuField);
        ConsoleLogger.getInstance()
                     .toConsole("Row check = " + rowCheck);

        boolean sliceCheck = sliceCheck(sudokuField);
        ConsoleLogger.getInstance()
                     .toConsole("Slice check = " + sliceCheck);

        cubeCheck(sudokuField);
    }

    private boolean setHasHiddenElements(Field sudokuField) {
        int[][] sudokuFields = sudokuField.getSudokuFields();
        for (int i = 0; i < Field.DIM_SIZE; i++) {
            for (int j = 0; j < Field.DIM_SIZE; j++) {
                if (sudokuFields[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean rowCheck(Field sudokuField) {
        Set<Integer> setOfInts = new HashSet<>();
        for (int[] row : sudokuField.getSudokuFields()) {
            setOfInts.clear();
            for (int element : row) {
                if (element != 0) {
                    if (!setOfInts.add(element)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean sliceCheck(Field sudokuField) {
        Set<Integer> setOfInts = new HashSet<>();
        int[][] sudokuFields = sudokuField.getSudokuFields();
        for (int col = 0; col < sudokuFields.length; col++) {
            setOfInts.clear();
            for (int row = 0; row < sudokuFields[col].length; row++) {
                int element = sudokuFields[row][col];
                if (element != 0) {
                    if (!setOfInts.add(element)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean cubeCheck(Field sudokuField) {
        for (int i = 0; i < Field.DIM_SIZE; i++) {
            for (Integer cubePosition : sudokuField.getCubePositions(i)) {
                sudokuField.showField(cubePosition);
            }
            ConsoleLogger.getInstance().toConsole("");
        }
//        for (List<Integer> value : cubeMap.values()) {
//            for (Integer integer : value) {
//                sudokuField.showField(integer);
//            }
//            ConsoleLogger.getInstance().toConsole("");
//        }
        return false;
    }
}

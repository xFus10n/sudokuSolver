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

        boolean cubeCheck = cubeCheck(sudokuField);
        ConsoleLogger.getInstance()
                     .toConsole("Cube check = " + cubeCheck);

        boolean valid = rowCheck && sliceCheck && cubeCheck;

        if (hasHiddenElements) {
            if (valid) {
                ConsoleLogger.getInstance().toConsole("Validated");
            } else ConsoleLogger.getInstance().toConsole("Failed");
        } else {
            if (valid) {
                ConsoleLogger.getInstance().toConsole("Solved");
            } else ConsoleLogger.getInstance().toConsole("Failed");
        }
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
                if (contains(setOfInts, element)) {
                    return false;
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
                if (contains(setOfInts, element)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean contains(Set<Integer> setOfInts, int element) {
        if (element != 0) {
            return !setOfInts.add(element);
        }
        return false;
    }

    private boolean cubeCheck(Field sudokuField) {
        Set<Integer> setOfInts = new HashSet<>();
        for (int i = 0; i < Field.DIM_SIZE; i++) {
            setOfInts.clear();
            for (Integer cubePosition : sudokuField.getCubePositions(i)) {
                int element = sudokuField.getField(cubePosition);
                if (contains(setOfInts, element)) {
                    return false;
                }
            }
        }
        return true;
    }
}

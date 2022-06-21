package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.*;

public class Validate implements Action {

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

        boolean hasHiddenElements = setHasHiddenElements(sudokuField);
        boolean rowCheck = rowCheck(sudokuField);
        boolean sliceCheck = sliceCheck(sudokuField);
        boolean cubeCheck = cubeCheck(sudokuField);
        boolean valid = rowCheck && sliceCheck && cubeCheck;

        if (hasHiddenElements) {
            if (valid) {
                sudokuField.setValidatedStatus();
            } else sudokuField.setFailedStatus();
        } else {
            if (valid) {
                sudokuField.setSolvedStatus();
            } else sudokuField.setFailedStatus();
        }
        ConsoleLogger.getInstance().toConsole("sudoku fields status is : " + sudokuField.getStatus());
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

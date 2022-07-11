package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.utils.Utilz;

import java.util.List;

import static com.sudoku.Field.FIELD_CAPACITY;

public class Solve implements Action {
    private static int iterations;
    private static ConsoleLogger logger = ConsoleLogger.getInstance();
    private static final int SOLVABLE_AMOUNT_ELEMENTS = 17;

    @Override
    public int id() {
        return 6;
    }

    @Override
    public String name() {
        return "solve sudoku puzzle";
    }

    @Override
    public void execute(Field sudokuField) {
        iterations = 0;
        boolean solvable = solvable(sudokuField);
        logger.toConsole("Sudoku is solvable = " + solvable);
        if (!solvable) {
            return;
        }
        boolean solved = solve(sudokuField);
        logger.toConsole(""); //new line for showing iterator
        logger.toConsole("Sudoku has been solved = " + solved);
    }

    private static boolean solvable(Field sudokuField) {
        int[][] fields = sudokuField.getSudokuFields();
        int count = 0;
        for (int[] field : fields) {
            for (int i : field) {
                if (0 == i) {
                    count++;
                }
            }
            if (FIELD_CAPACITY - count < SOLVABLE_AMOUNT_ELEMENTS) {
                return false;
            }
        }
        return true;
    }

    private static boolean solve(Field sudokuField) {
        logger.toConsole("Iterations: " + ++iterations + '\r', true);
        try {
            for (int i = 0; i < FIELD_CAPACITY; i++) {
                int fieldValue = sudokuField.getFieldValue(i);
                if (fieldValue == 0) {
                    List<Integer> positionCandidates = sudokuField.getPositionCandidates(i);
                    if (positionCandidates.isEmpty()) {
                        return false;
                    }
                    for (Integer candidate : positionCandidates) {
                        sudokuField.setField(i, candidate);

                        if (solve(sudokuField)) {
                            return true;
                        } else {
                            Utilz.undo(sudokuField);
                        }
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

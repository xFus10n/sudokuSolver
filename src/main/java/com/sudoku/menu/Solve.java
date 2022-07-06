package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import static com.sudoku.Field.FIELD_CAPACITY;

public class Solve implements Action{
    public static final  int SOLVABLE_AMOUNT_ELEMENTS = 17;

    @Override
    public int id() {
        return 6;
    }

    @Override
    public String name() {
        return "solve sudoku puzzle (in dev)";
    }

    @Override
    public void execute(Field sudokuField) {
        boolean solvable = solvable(sudokuField);
        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.toConsole("Sudoku is solvable = " + solvable);
//        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
//            List<Integer> candidates = sudokuField.getCandidates(i);
//            if ((sudokuField.getField(i) == 0) && (candidates.size() == 1)) {
//                logger.toConsole("Position: " + i + " has only one value = " + candidates.get(0));
//            }
//        }
        //todo: implement

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
}

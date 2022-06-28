package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.List;

public class Solve implements Action{
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
        boolean solvable = sudokuField.solvable();
        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.toConsole("Sudoku is solvable = " + solvable);
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            List<Integer> candidates = sudokuField.getCandidates(i);
            if ((sudokuField.getField(i) == 0) && (candidates.size() == 1)) {
                logger.toConsole("Position: " + i + " has only one value = " + candidates.get(0));
            }
        }
        //todo: implement

    }
}
